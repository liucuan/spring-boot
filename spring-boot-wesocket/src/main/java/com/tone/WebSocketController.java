package com.tone;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * @author zhaoxiang.liu
 * @date 2018/7/3
 */
@Controller
public class WebSocketController {

    @MessageMapping("/welcome")
    @SendTo("/topic/getResponse")
    public Response welcome(Message message) {
        return new Response("Message: " + message.getMessage());
    }

}
