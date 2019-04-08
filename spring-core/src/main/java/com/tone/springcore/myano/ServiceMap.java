package com.tone.springcore.myano;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class ServiceMap {

    private Map<String, MyService> map = new HashMap<>();

    public void go(String type) {
        map.get(type).go();
    }

    public Map<String, MyService> getMap() {
        return map;
    }

    public void setMap(Map<String, MyService> map) {
        this.map = map;
    }
}
