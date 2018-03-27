package com.tone.github;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class DemoService {
    private final Logger logger = LoggerFactory.getLogger(DemoService.class);

    @Cacheable(cacheNames = "demo", key = "'DemoService:'+#id")
    public DemoBean getStr(String id) {
        try {
            TimeUnit.MILLISECONDS.sleep(200);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("getStr with id={} from service.", id);
        return new DemoBean(id, "name" + id);
    }
}
