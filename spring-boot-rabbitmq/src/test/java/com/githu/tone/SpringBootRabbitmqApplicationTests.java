package com.githu.tone;

import com.githu.tone.mq.Producer;
import com.githu.tone.mq.Producer2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootRabbitmqApplicationTests {
    @Autowired
    private Producer producer;
    @Autowired
    private Producer2 producer2;

    @Test
    public void produce() {
        producer.send();
        producer2.send();
        while (true) {

        }
    }

}
