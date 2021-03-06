package com.githu.tone.mq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by jenny on 2017/5/31.
 */
@Component
public class Producer {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() {
//        String context = "hello " + new Date();
//        System.out.println("Producer : " + context);
        Message msg = new Message();
        msg.setId(100);
        msg.setVaule("msg100");
        System.out.println("Producer : " + msg);
        this.rabbitTemplate.convertAndSend("hello", msg);
    }
}
