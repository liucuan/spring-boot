package com.tone.my.anno;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author zhaoxiang.liu
 * @date 2018/7/18
 */
public class MyImportSelector implements ImportSelector {

    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        System.out.println(
                importingClassMetadata.getAnnotationAttributes(EnableMyApp.class.getName()));
        if (importingClassMetadata.getAnnotationAttributes(EnableMyApp.class.getName()) != null
                && importingClassMetadata.getAnnotationAttributes(EnableMyApp.class.getName())
                .toString().contains("true")) {

            return new String[]{MyAppConfiguration.class.getName()};
        }

        return new String[]{};
    }
}
