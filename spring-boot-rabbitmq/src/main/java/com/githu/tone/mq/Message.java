package com.githu.tone.mq;

import java.io.Serializable;

public class Message implements Serializable {
    private static final long serialVersionUID = 1823239785430177012L;
    private long id;
    private String vaule;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getVaule() {
        return vaule;
    }

    public void setVaule(String vaule) {
        this.vaule = vaule;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", vaule='" + vaule + '\'' +
                '}';
    }
}
