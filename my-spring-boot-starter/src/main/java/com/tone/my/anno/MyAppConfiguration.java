package com.tone.my.anno;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhaoxiang.liu
 * @date 2018/7/18
 */
@Configuration
@EnableConfigurationProperties(MyAppProperties.class)
public class MyAppConfiguration {

    @Autowired
    MyAppProperties myAppProperties;

    @Bean
    public MyApp studentBean() {
        MyApp myApp = new MyApp();
        myApp.setId(myAppProperties.getId());
        myApp.setName(myAppProperties.getName());
        return myApp;
    }
}
