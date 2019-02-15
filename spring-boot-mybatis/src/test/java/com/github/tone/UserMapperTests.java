package com.github.tone;

import com.github.tone.dao.UserMapper;
import com.github.tone.service.TargetService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTests {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    TargetService targetService;

    @Test
    public void ts() {
        targetService.a();
    }

    @Test
    @Transactional
    public void findByName() throws Exception {
//        userMapper.insert("AAA", 20);

        System.out.println("userMapper读取数据: " + userMapper.findByName("AAA"));
        System.out.println("userMapper读取数据: " + userMapper.findByName("AAA"));
        System.out.println(
                "userMapper更新了" + userMapper.update("AAA", 32) + "个学生的数据");
        System.out.println("userMapper读取数据: " + userMapper.findByName("AAA"));
        System.out.println("userMapper读取数据: " + userMapper.findByName("AAA"));

    }

}
