package com.github.tone.service;

import com.github.tone.fw.BeanSelfAware;
import org.springframework.stereotype.Service;

/**
 * @author zhaoxiang.liu
 * @date 2018/10/19
 */
@Service
public class Bean2 implements BeanSelfAware {

    @Override
    public void setSelf(Object obj) {
        System.out.println("b2");
    }
}
