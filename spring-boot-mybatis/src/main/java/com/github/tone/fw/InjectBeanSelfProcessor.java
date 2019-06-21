package com.github.tone.fw;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanCurrentlyInCreationException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author zhaoxiang.liu
 * @date 2018/10/19
 */
@Slf4j
@Order(100000)
@Component
public class InjectBeanSelfProcessor implements BeanPostProcessor, ApplicationContextAware {

    ApplicationContext context;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName)
            throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName)
            throws BeansException {
        if (bean instanceof BeanSelfAware) {//如果Bean实现了BeanSelfAware标识接口，就将代理对象注入
            BeanSelfAware myBean = (BeanSelfAware) bean;
            Class cls = bean.getClass();
            if (!AopUtils.isAopProxy(bean)) {
                Class c = bean.getClass();
                Service serviceAnnotation = (Service) c.getAnnotation(Service.class);
                if (serviceAnnotation != null) {
                    try {
                        bean = context.getBean(beanName);
                        if (AopUtils.isAopProxy(bean)) {
                        }
                    } catch (BeanCurrentlyInCreationException beancurrentlyincreationexception) {
                    } catch (Exception ex) {
                        log.error((new StringBuilder()).append("No Proxy Bean for service ")
                                .append(bean.getClass()).append(" ").append(ex.getMessage())
                                .toString(), ex);
                    }
                }
            }
            myBean.setSelf(bean);
            return myBean;
        } else {
            return bean;
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
