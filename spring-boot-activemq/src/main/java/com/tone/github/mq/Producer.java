package com.tone.github.mq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Destination;

@Component
public class Producer {
    @Autowired
    private JmsTemplate jmsTemplate;

    // 发送消息，destination是发送到的队列，message是待发送的消息
    public String sendMessage(Destination destination, final String message) {
        jmsTemplate.convertAndSend(destination, message);
        return "ok";
    }
}
