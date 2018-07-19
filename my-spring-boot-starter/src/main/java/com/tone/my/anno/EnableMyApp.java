package com.tone.my.anno;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.context.annotation.Import;

/**
 * @author zhaoxiang.liu
 * @date 2018/7/18
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
//MyImportBeanDefinitionRegistrar 动态注入 MyImportSelector静态注入
@Import(MyImportSelector.class)
public @interface EnableMyApp {

    boolean value() default true;
}
