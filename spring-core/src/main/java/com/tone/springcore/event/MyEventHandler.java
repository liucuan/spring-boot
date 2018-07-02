package com.tone.springcore.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author zhaoxiang.liu
 * @date 2018/7/2
 */
@Component
@Slf4j
public class MyEventHandler {

    @EventListener
    public void handleEvent(MyEvent event) {
        log.info("------------处理事件：{}", event.getMsg());
        try {
            Thread.sleep(5 * 1000L);
            log.info("事件1(5s)处理完成");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @EventListener
    public void handleEvent2(MyEvent2 event) {
        log.info("------------处理事件2：{}", event.getMsg());
        try {
            Thread.sleep(10 * 1000L);
            log.info("事件2(10s)处理完成");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
