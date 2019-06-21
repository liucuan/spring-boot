package com.github.tone.service;

import com.github.tone.dao.UserMapper;
import com.github.tone.fw.BeanSelfAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zhaoxiang.liu
 * @date 2018/10/19
 */
@Service
public class TargetServiceImpl implements TargetService, BeanSelfAware {

    @Autowired
    UserMapper userMapper;
    private TargetService self;

    @Override
    public void setSelf(Object proxyBean) { //通过InjectBeanSelfProcessor注入自己（目标对象）的AOP代理对象
        this.self = (TargetService) proxyBean;
    }

    @Override
    public void a() {
        self.b();
    }

    @Transactional
    @Override
    public void b() {
        //执行数据库操作
        userMapper.insert("t", 23);
        throw new RuntimeException("xx");
    }
}
