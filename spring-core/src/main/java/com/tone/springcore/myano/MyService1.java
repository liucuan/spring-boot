package com.tone.springcore.myano;

import org.springframework.stereotype.Component;

@MyComponent(type = "t1")
@Component
public class MyService1 implements MyService{

    @Override
    public void go() {
        System.out.println("1 go");
    }
}
