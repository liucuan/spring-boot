package com.tone.springcore.myano;

import org.springframework.stereotype.Component;

@MyComponent(type = "t2")
@Component
public class MyService2 implements MyService {

    @Override
    public void go() {
        System.out.println("2 go");
    }
}
