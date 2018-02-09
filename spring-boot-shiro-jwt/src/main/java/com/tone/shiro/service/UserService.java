package com.tone.shiro.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.tone.shiro.entity.DataSource;
import com.tone.shiro.entity.UserBean;

/**
 * @author zhaoxiang.liu
 * @date 2018/2/9
 */
@Service
public class UserService {
    public UserBean getUser(String username) {
        // 没有此用户直接返回null
        if(!DataSource.getData().containsKey(username)) {
            return null;
        }
        UserBean user = new UserBean();
        Map<String, String> detail = DataSource.getData().get(username);
        user.setUsername(username);
        user.setPassword(detail.get("password"));
        user.setRole(detail.get("role"));
        user.setPermission(detail.get("permission"));
        return user;
    }
}
