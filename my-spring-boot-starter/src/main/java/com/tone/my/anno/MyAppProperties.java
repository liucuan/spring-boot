package com.tone.my.anno;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author zhaoxiang.liu
 * @date 2018/7/16
 */
@ConfigurationProperties(prefix = "my.app")
public class MyAppProperties {

    private int id = 11;
    private String name = "admin";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
