package com.tone.springcore.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

/**
 * @author zhaoxiang.liu
 * @date 2018/7/2
 */
@Component
@Slf4j
public class EventService implements ApplicationEventPublisherAware {

    public ApplicationEventPublisher publisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }

    public String doEventWork(String msg) {
        log.info("------------publish event:" + msg);
        MyEvent event = new MyEvent(this, msg);
        publisher.publishEvent(event);
        return "OK";
    }

    public String doEventWork2(String msg) {
        log.info("------------publish event2:" + msg);
        MyEvent2 event2 = new MyEvent2(this, msg);
        publisher.publishEvent(event2);
        return "OK";
    }

}
