package com.winton.alarmclock.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by winton on 2017/2/23.
 */
@Entity
public class TestBean {
    @Id
    private Long id;

    @NotNull
    private String name;

    @Generated(hash = 1986301890)
    public TestBean(Long id, @NotNull String name) {
        this.id = id;
        this.name = name;
    }

    @Generated(hash = 2087637710)
    public TestBean() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
