package com.tone.springcore;

import com.tone.springcore.myano.ServiceMap;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringCoreApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringCoreApplication.class, args);
        ServiceMap sm = context.getBean(ServiceMap.class);
        sm.go("t1");
        sm.go("t2");
    }
}
