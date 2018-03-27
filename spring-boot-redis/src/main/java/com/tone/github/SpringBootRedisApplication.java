package com.tone.github;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringBootRedisApplication {
    public static final Logger LOGGER = LoggerFactory.getLogger(SpringBootRedisApplication.class);

    // @Bean
    // RedisMessageListenerContainer container(RedisConnectionFactory factory, MessageListenerAdapter adapter) {
    // RedisMessageListenerContainer container = new RedisMessageListenerContainer();
    // container.setConnectionFactory(factory);
    // container.addMessageListener(adapter, new PatternTopic("chat"));
    // return container;
    // }
    //
    // @Bean
    // MessageListenerAdapter listenerAdapter(Receiver receiver) {
    // return new MessageListenerAdapter(receiver, "receiveMessage");
    // }
    //
    // @Bean
    // Receiver receiver(CountDownLatch latch) {
    // return new Receiver(latch);
    // }
    //
    // @Bean
    // CountDownLatch latch() {
    // return new CountDownLatch(1);
    // }
    //
    // @Bean
    // StringRedisTemplate template(RedisConnectionFactory connectionFactory) {
    // return new StringRedisTemplate(connectionFactory);
    // }
    //
    // @Bean("jsonTemplate")
    // StringRedisTemplate jsonTemplate(RedisConnectionFactory connectionFactory) {
    // return new StringRedisTemplate(connectionFactory);
    // }
    public static void main(String[] args) throws InterruptedException {
        ApplicationContext ctx = SpringApplication.run(SpringBootRedisApplication.class, args);
        // StringRedisTemplate template = ctx.getBean(StringRedisTemplate.class);
        // CountDownLatch latch = ctx.getBean(CountDownLatch.class);
        // LOGGER.info("Sending message....");
        // template.convertAndSend("chat", "Hello from Redis!");
        // latch.await();
        // System.exit(0);
    }
}
