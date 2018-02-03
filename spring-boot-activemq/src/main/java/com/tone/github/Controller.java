package com.tone.github;

import com.tone.github.mq.Producer;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class Controller {
    @Autowired
    private Producer producer;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/produce")
    public String produce() {
        producer.sendMessage(new ActiveMQQueue("my.queue"), "hello");
        return "ok";
    }

    @RequestMapping("/produce2")
    public Mono<String> produce2() {
        return Mono.just(producer.sendMessage(new ActiveMQQueue("my.queue"), "hello"));
    }
}
