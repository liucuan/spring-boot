package com.tone;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * @author zhaoxiang.liu
 * @date 2018/7/3
 */
@Configuration
// 该注解开启使用STOMP协议来传输基于代理(message broker)的消息，
// 此时控制器支持使用@MessageMapping
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override// 注册STOMP协议的节点(endpoint)，并映射指定的URL
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 注册一个STOMP的endpoint，并指定使用SockJS协议
        registry.addEndpoint("endpointAir").withSockJS();
    }

    @Override// 配置消息代理(Message Broker)
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 配置一个广播式消息代理："/topic"
        registry.enableSimpleBroker("/topic");
    }

}
