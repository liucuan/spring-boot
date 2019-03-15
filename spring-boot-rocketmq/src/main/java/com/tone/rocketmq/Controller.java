package com.tone.rocketmq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    private RocketMQServer rocketMQServer;

    @GetMapping("/send")
    public String send() {
        rocketMQServer.producer();
        return "ok";
    }
}
