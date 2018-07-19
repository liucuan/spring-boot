package com.tone;

import com.tone.my.MyService;
import com.tone.my.anno.MyApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhaoxiang.liu
 * @date 2018/7/16
 */
@RestController
public class Controller {

    @Autowired
    private MyService myService;
    @Autowired
    private MyApp myApp;

    @GetMapping("/input")
    public String input(String word) {
        return myService.wrap(word);
    }

    @GetMapping("/app")
    public String app() {
        return myApp.getId() + "," + myApp.getName();
    }
}
