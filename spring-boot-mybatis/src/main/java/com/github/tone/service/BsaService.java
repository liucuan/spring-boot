package com.github.tone.service;

import com.github.tone.fw.BeanSelfAware;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author zhaoxiang.liu
 * @date 2018/10/19
 */
@Component
public class BsaService implements BeanPostProcessor, ApplicationContextAware {

    Map<String, BeanSelfAware> bsaBeans = new HashMap<>();
    ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName)
            throws BeansException {
        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName)
            throws BeansException {
        if (bean instanceof BeanSelfAware) {
            bsaBeans.put(beanName, (BeanSelfAware) bean);
        }
        return bean;
    }
}
