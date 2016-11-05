package com.github.tone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by jenny on 2016/11/4.
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration
public class SampleWarApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(SampleWarApplication.class, args);
    }

}
