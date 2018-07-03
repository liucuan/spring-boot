package com.tone;

import java.io.Serializable;

/**
 * @author zhaoxiang.liu
 * @date 2018/7/3
 */
public class Response implements Serializable {

    private String message;

    public Response(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
