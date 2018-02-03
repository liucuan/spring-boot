package com.tone.github.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Customer {
    private static final Logger LOGGER = LoggerFactory.getLogger(Customer.class);

    @JmsListener(destination = "my.queue")
    public void customer(String msg) {
        LOGGER.info("customer received: " + msg);
    }
}
