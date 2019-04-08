package com.tone.springcore.myano;

import java.util.Map;
import java.util.Map.Entry;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

//@Component
public class MyServiceListener implements ApplicationListener<ContextRefreshedEvent> {


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        //获取@JalorOperation注解的所有bean
        Map<String, Object> map = event.getApplicationContext()
            .getBeansWithAnnotation(MyComponent.class);
        ServiceMap serviceMap = event.getApplicationContext().getBean(ServiceMap.class);
        for (Entry<String, Object> entry : map.entrySet()) {
            MyComponent mc = AnnotationUtils.findAnnotation(entry.getValue().getClass(), MyComponent.class);
            serviceMap.getMap().put(mc.type(), (MyService) entry.getValue());
        }
        System.out.println("serviceMap=" + serviceMap);
    }
}
