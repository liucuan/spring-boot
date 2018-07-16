package com.tone.my;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author zhaoxiang.liu
 * @date 2018/7/16
 */
@ConfigurationProperties(prefix = "my.service")
public class MyServiceProperties {

    private String prefix;
    private String suffix;

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}
