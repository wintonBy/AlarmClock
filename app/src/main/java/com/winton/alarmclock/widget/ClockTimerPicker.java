package com.winton.alarmclock.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by winton on 2016/12/20.
 */

public class ClockTimerPicker  extends View{

    private List<String> valueList;
    private Paint mPaint;

    private static final float SPEED = 2;
    private static final float MARGIN_ALPHA = 2.8f;
    private static final int MOVE_UP = -1;
    private static final int MOVE_DOWN = 1;

    private float minTextSize = 60;
    private float maxTextSize = 80;
    private float minAlpha = 125f;
    private float maxAlpha = 255f;
    private boolean isInit = false;
    private int selectTextColor = Color.WHITE;
    private int unselectTextColor = Color.BLACK;


    private int viewHeight;
    private int viewWidth;
    private float moveLength = 0f;
    private float lastDownY = 0f;
    private int currentSelect = 0;

    private MyTimerTask timerTask;
    private Timer timer;
    private OnSelectListener onSelectListener;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(Math.abs(moveLength) < SPEED){
                moveLength = 0;
                if(timerTask != null){
                    timerTask.cancel();
                    timerTask = null;
                    doSelect();
                }
            }else {
                moveLength = moveLength - moveLength / Math.abs(moveLength) * SPEED;
            }
            invalidate();
        }
    };


    public ClockTimerPicker(Context context) {
        super(context,null);
    }

    public ClockTimerPicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        timer = new Timer();
        valueList = new ArrayList<>();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextAlign(Paint.Align.CENTER);
        currentSelect = valueList.size()/2;
    }
    private void doSelect(){
        if(onSelectListener != null ){
            onSelectListener.onSelect(valueList.get(currentSelect));
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        viewHeight = getMeasuredHeight();
        viewWidth = getMeasuredWidth();
        // 按照View的高度计算字体大小
        maxTextSize = viewHeight / 4.0f;
        minTextSize = maxTextSize / 2.0f;
        isInit = true;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(isInit && valueList.size() >0){
            drawData(canvas);
        }
    }

    /**
     * @param canvas
     *
     */
    private void drawData(Canvas canvas){
        float scale = parabola(viewHeight/4.0f,moveLength);
        float textSize = minTextSize +(maxTextSize - minTextSize) * scale;
        mPaint.setTextSize(textSize);
        float alpha = minAlpha + (maxAlpha-minAlpha)*scale;
        mPaint.setAlpha((int)alpha);
        mPaint.setColor(selectTextColor);
        float x = (viewWidth/2);
        float y = viewHeight/2 + moveLength;
        Paint.FontMetricsInt fmi = mPaint.getFontMetricsInt();
        float baseline =  y - (fmi.bottom/2 + fmi.top/2);
        canvas.drawText(valueList.get(currentSelect),x,baseline,mPaint);

        /*绘制上方*/
        for(int i=1; (currentSelect - i) >= 0; i++){
            drawOtherData(canvas,i,MOVE_UP);
        }
        /*绘制下方*/
        for(int i=1;(currentSelect + i) <valueList.size(); i++){
            drawOtherData(canvas,i,MOVE_DOWN);
        }

    }

    private void drawOtherData(Canvas canvas ,int position, int type){
        float d = MARGIN_ALPHA * minTextSize * position + type*moveLength;
        float scale = parabola(viewHeight/4.0f ,d);
        float textSize = minTextSize + (maxTextSize - minTextSize)*scale;
        mPaint.setTextSize(textSize);
        mPaint.setColor(unselectTextColor);
        float alpha = minAlpha + (maxAlpha-minAlpha)*scale;
        mPaint.setAlpha((int)alpha);
        float y = (float) (viewHeight / 2.0 + type * d);
        Paint.FontMetricsInt fmi = mPaint.getFontMetricsInt();
        float baseline = (float) (y - (fmi.bottom / 2.0 + fmi.top / 2.0));
        canvas.drawText(valueList.get(currentSelect + type * position), (float) (viewWidth / 2.0), baseline, mPaint);

    }

    public void setOnSelectListener(OnSelectListener onSelectListener) {
        this.onSelectListener = onSelectListener;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()){
            case MotionEvent.ACTION_DOWN:
                doDown(event);
                break;
            case MotionEvent.ACTION_MOVE:
                doMove(event);
                break;
            case MotionEvent.ACTION_UP:
                doUp(event);
                break;
        }
        return true;
    }
    /**
     *
     * @param event
     * 响应手指按下时的动作
     */
    private void doDown(MotionEvent event){
        if(timerTask != null ){
            timerTask.cancel();
            timerTask = null;
        }
        lastDownY = event.getY();
    }

    private void doMove(MotionEvent event){
        moveLength+=  ( event.getY() - lastDownY);
        if(moveLength >  MARGIN_ALPHA * minTextSize / 2){
            //下滑
            moveTailToHead();
            moveLength = moveLength - MARGIN_ALPHA * minTextSize;

        }
        if(moveLength < -(MARGIN_ALPHA * minTextSize /2)){
            //上滑
            moveHeadToTail();
            moveLength = moveLength +  MARGIN_ALPHA * minTextSize;
        }
        lastDownY = event.getY();
        invalidate();
    }

    private void doUp(MotionEvent event){
        if(Math.abs(moveLength) < 0.0001){
            moveLength = 0;
            return;
        }
        if(timerTask != null){
            timerTask.cancel();
            timerTask = null;
        }
        timerTask = new MyTimerTask(mHandler);
        timer.schedule(timerTask,0,10);
    }

    private void moveHeadToTail(){
        if(valueList == null || valueList.size() == 0){
            return;
        }
        String head = valueList.get(0);
        valueList.remove(0);
        valueList.add(head);
    }
    private void moveTailToHead(){
        if(valueList == null || valueList.size() == 0){
            return;
        }
        String tail = valueList.get(valueList.size()-1);
        valueList.remove(valueList.size()-1);
        valueList.add(0,tail);
    }

    /**
     * 填充数据
     * @param data
     */
    public void setData(List<String> data){
        valueList = new ArrayList<>();
        valueList.addAll(data);
        currentSelect = valueList.size() /2;
        invalidate();
    }
    public String getValue(){
        String result = null;
        if(valueList != null){
            result = valueList.get(currentSelect);
        }
        return result;
    }


    private float parabola(float zero,float x){
        float f = (float)(1 - Math.pow(x/zero,2));
        return f < 0 ? 0 : f;
    }

    class MyTimerTask extends TimerTask{

        Handler handler;
        public MyTimerTask(Handler handler) {
            this.handler = handler;
        }

        @Override
        public void run() {
            handler.sendMessage(handler.obtainMessage());
        }
    }

    public interface OnSelectListener{
        void onSelect(String value);
    }
}
