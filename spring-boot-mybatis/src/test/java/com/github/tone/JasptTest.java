package com.github.tone;

import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zhaoxiang.liu
 * @date 2019/2/15
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class JasptTest {

    @Autowired
    StringEncryptor stringEncryptor;

    @Test
    public void encryptPwd() {
        String result = stringEncryptor.encrypt("root");
        System.out.println("==================");
        System.out.println(result);
        System.out.println("==================");
    }
}
