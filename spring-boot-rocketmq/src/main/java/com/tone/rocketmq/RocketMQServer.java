package com.tone.rocketmq;

import java.io.UnsupportedEncodingException;
import javax.annotation.PostConstruct;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
public class RocketMQServer {

    @Value("${apache.rocketmq.producer.producerGroup}")
    private String producerGroup;
    @Value("${apache.rocketmq.namesrvAddr}")
    private String namesrvAddr;

    public void producer() {
        DefaultMQProducer producer = new DefaultMQProducer(producerGroup);
        //多地址以;隔开
        producer.setNamesrvAddr(namesrvAddr);
        try {
            //必须初始化一次,不可多次
            producer.start();
            //发送消息
            Message message = new Message("TopicTest", "test",
                "say hi.".getBytes(RemotingHelper.DEFAULT_CHARSET));
            StopWatch sw = new StopWatch();
            sw.start();
            for (int i = 0; i < 10000; i++) {
                SendResult result = producer.send(message);
                System.out.println(
                    "发送消息: MsgId=" + result.getMsgId() + ",发送状态:" + result.getSendStatus());
            }
            sw.stop();
            System.out.println("发送1w条消息耗时：" + sw.getTotalTimeMillis() + "ms");
        } catch (MQClientException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (RemotingException e) {
            e.printStackTrace();
        } catch (MQBrokerException e) {
            e.printStackTrace();
        } finally {
            producer.shutdown();
        }
    }
}
