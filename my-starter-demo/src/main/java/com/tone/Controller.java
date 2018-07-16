package com.tone;

import com.tone.my.MyService;
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

    @GetMapping("/input")
    public String input(String word) {
        return myService.wrap(word);
    }
}
