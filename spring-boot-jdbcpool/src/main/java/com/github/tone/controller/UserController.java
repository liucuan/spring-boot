package com.github.tone.controller;

import com.github.tone.dao.UserMapper;
import com.github.tone.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

/**
 * Created by echolau on 2017/6/20.
 */
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/add")
    public User addUser() {
        String name = "tone";
        int age = new Random().nextInt(55);
        userMapper.insert(name, age);
        User user = new User();
        user.setAge(age);
        user.setName(name);
        return user;
    }

    @RequestMapping("/all")
    public List<User> queryAll() {
        return userMapper.findAll();
    }

}
