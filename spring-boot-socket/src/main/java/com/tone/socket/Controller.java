package com.tone.socket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhaoxiang.liu
 * @date 2019/6/6
 */
@RestController
public class Controller {

    @Autowired
    private SocketIOService socketIOService;

    @RequestMapping("/push")
    public void pushEvent(@RequestParam("uid") String uid, @RequestParam("msg") String msg) {
        PushMessage message = new PushMessage(uid, msg);

        socketIOService.pushMessageToUser(message);
    }

    @RequestMapping("/stop")
    public void stop() {
        socketIOService.stop();
    }
}
