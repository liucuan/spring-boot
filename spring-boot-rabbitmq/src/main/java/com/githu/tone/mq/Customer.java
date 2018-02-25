package com.githu.tone.mq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by jenny on 2017/5/31.
 */
@Component
@RabbitListener(queues = "hello")
public class Customer {
    @RabbitHandler
    public void process(Message msg) {
        System.out.println("------------ " + msg);
    }

    @RabbitHandler
    public void processString(String msg) {
        System.out.println("+++++++++++++ " + msg);
    }
}
