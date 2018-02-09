package com.tone.shiro.entity;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author zhaoxiang.liu
 * @date 2018/2/9
 */
public class JWTToken implements AuthenticationToken {
    // 密钥
    private String token;

    public JWTToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
