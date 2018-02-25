package com.tone.github;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

//@Component
public class LogService {
    private static final Logger LOGGER = LogManager.getLogger(LogService.class);

    @Scheduled(fixedRate = 2000L)
    public void exe() {
        Date now = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        LOGGER.info(simpleDateFormat.format(now));
        LOGGER.debug("-------DEBUG---------");
        LOGGER.error(now.getTime());

//        LOGGER.debug("this is debug logger.");
//        LOGGER.info("this is info logger.");
//        LOGGER.warn("this is warn logger.");
//        LOGGER.error("this is error logger.");
    }
}
