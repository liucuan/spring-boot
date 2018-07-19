package com.tone;

import com.tone.my.anno.EnableMyApp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableMyApp
@SpringBootApplication
public class MyStarterDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyStarterDemoApplication.class, args);
    }
}
