package com.githu.tone.mq;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    TransportClient client;

    @KafkaListener(topics = "test1")
    public void processMessage(String content) {
        Message m = gson.fromJson(content, Message.class);
        logger.info("recieved message:{},date:{}", m.getMsg(), new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(m.getSendTime()));
    }

    @KafkaListener(topics = "logTopic")
    public void logTopic(String content) {
//        Message m = gson.fromJson(content, Message.class);
        logger.info("recieved message:{}", content);
        IndexResponse response = client.prepareIndex("bizlog", "bizlog").setSource(content, XContentType.JSON).get();
        logger.info("recieved message:{}", response.getId());

    }
}
