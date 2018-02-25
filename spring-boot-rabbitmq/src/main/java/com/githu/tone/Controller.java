package com.githu.tone;

import com.githu.tone.mq.Producer;
import com.githu.tone.mq.Producer2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @Autowired
    private Producer producer;
    @Autowired
    private Producer2 producer2;

    @RequestMapping("/produce")
    public String produce() {
        producer.send();
        return "produce msg success.";
    }

    @RequestMapping("/Producer2")
    public String producer2() {
        producer.send();
        return "produce2 msg success.";
    }

}
