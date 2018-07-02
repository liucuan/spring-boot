package com.tone.springcore.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author zhaoxiang.liu
 * @date 2018/7/2
 */

public class MyEvent extends ApplicationEvent {

    private String msg;

    public MyEvent(Object source, String msg) {
        super(source);
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
