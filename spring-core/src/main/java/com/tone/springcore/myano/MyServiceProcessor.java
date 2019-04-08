package com.tone.springcore.myano;

import java.util.Map;
import java.util.Map.Entry;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

@Component
public class MyServiceProcessor extends ApplicationObjectSupport implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName)
        throws BeansException {
        if (bean instanceof MyService) {
            MyComponent mc = AnnotationUtils.findAnnotation(bean.getClass(), MyComponent.class);
            if (mc != null) {
                ServiceMap serviceMap = getApplicationContext().getBean(ServiceMap.class);
                serviceMap.getMap().put(mc.type(), (MyService) bean);
            }
        }
        return bean;
    }
}