package com.tone.shiro;

/**
 * @author zhaoxiang.liu
 * @date 2018/2/9
 */
public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String msg) {
        super(msg);
    }

    public UnauthorizedException() {
        super();
    }
}
