package com.tone.my;

/**
 * @author zhaoxiang.liu
 * @date 2018/7/16
 */
public class MyService {

    private String prefix;
    private String suffix;

    public MyService() {
    }

    public MyService(String prefix, String suffix) {
        this.prefix = prefix;
        this.suffix = suffix;
    }

    public String wrap(String word) {
        return prefix + word + suffix;
    }

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
