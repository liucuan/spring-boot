package com.tone.druid.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tone.druid.dao.UserDao;
import com.tone.druid.model.User;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User findById(Long id) {
        return userDao.findOne(id);
    }
}
