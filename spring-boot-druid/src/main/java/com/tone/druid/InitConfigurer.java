package com.tone.druid;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.tone.druid.dao.UserDao;
import com.tone.druid.model.User;

/**
 * @author zhaoxiang.liu
 * @date 2018/2/23
 */
@Configuration
public class InitConfigurer {
    @Autowired
    private UserDao userDao;

    @PostConstruct
    public void init() {
        for (int i = 1; i <= 100; i++) {
            userDao.save(new User("TEST-NAME-" + i));
        }
    }
}
