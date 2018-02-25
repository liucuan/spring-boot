package com.tone.github;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootLog4j2ApplicationTests {
    @Autowired
    private LogService logService;

    @Test
    public void contextLoads() {
        logService.exe();
    }

}
