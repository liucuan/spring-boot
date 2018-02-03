package com.tone.github.dubbo.provider;


import com.alibaba.dubbo.config.annotation.Service;
import com.tone.github.dubbo.api.DemoService;

@Service(version = "1.0.0",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}")
public class DemoServiceImpl implements DemoService {
    @Override
    public String sayHello(String s) {
        return "hello," + s;
    }
}
