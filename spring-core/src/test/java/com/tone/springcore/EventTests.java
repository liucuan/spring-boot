package com.tone.springcore;

import com.tone.springcore.event.EventService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EventTests {

    @Autowired
    private EventService service;

    @Test
    public void eventTest() {
        String msg = "Java Code";
        service.doEventWork(msg);
    }

    @Test
    public void event2Test() {
        String msg = "Java Code";
        service.doEventWork2(msg);
    }

}
