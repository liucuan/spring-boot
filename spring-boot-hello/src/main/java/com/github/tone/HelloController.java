package com.github.tone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jenny on 2017/5/24.
 */
@RestController
public class HelloController {
    @RequestMapping("/hello")
    public String index() {
        return "Hello World";
    }

    @Token(save = true)
    @RequestMapping("/savetoken")
    public String getToken(HttpServletRequest request, HttpServletResponse response) {
        return (String) request.getSession().getAttribute("token");
    }

    @Token(remove = true)
    @RequestMapping("/removetoken")
    public String removeToken(HttpServletRequest request, HttpServletResponse response) {
        return "success";
    }
}
