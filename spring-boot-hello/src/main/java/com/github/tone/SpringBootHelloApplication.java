package com.github.tone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringBootHelloApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringBootHelloApplication.class, args);
        TokenAspect ta = context.getBean(TokenAspect.class);
        System.out.println(ta);
    }
}
