package com.tone.mqtt;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author zhaoxiang.liu
 * @date 2018/5/14
 */
@Component
@ConfigurationProperties(prefix = "tone.mqtt")
public class MqttConfig {
    private String username;

    private String password;

    private String urls;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrls() {
        return urls;
    }

    public void setUrls(String urls) {
        this.urls = urls;
    }
}
