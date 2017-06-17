package com.githu.tone.mq;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

/**
 * Created by jenny on 2017/5/31.
 */
@Component
public class Customer {
    private static final Logger logger = LoggerFactory.getLogger(Customer.class);
    private Gson gson = new GsonBuilder().create();

    @KafkaListener(topics = "test1")
    public void processMessage(String content) {
        Message m = gson.fromJson(content, Message.class);
        logger.info("recieved message:{},date:{}", m.getMsg(), new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(m.getSendTime()));
    }
}
