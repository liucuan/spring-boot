package com.tone.github.bizlog;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@Component
public class JsonLogService {
    private static final Logger LOGGER = LogManager.getLogger(JsonLogService.class);
    Gson gson = new GsonBuilder().create();

    @Scheduled(fixedRate = 2000L)
    public void exe() {
        int duration = new Random().nextInt(500);
        BizzLogInfo bizzLogInfo = new BizzLogInfo();
        bizzLogInfo.setDuration(duration);
        bizzLogInfo.setAction(new Random().nextBoolean() ? "query" : "update");
        bizzLogInfo.setDataSource(new Random().nextBoolean() ? "elasticsearch" : "mysql");
        bizzLogInfo.setStatus(new Random().nextBoolean() ? 0 : 1);
        LOGGER.info(gson.toJson(bizzLogInfo));
    }
}
