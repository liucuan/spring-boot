package com.tone.github.bizlog;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

/**
 * Created by liuzhaoxiang on 2016/2/29.
 */
@Service
public class LogService implements InitializingBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogService.class);

    private static final Logger ROOT_LOGGER = LoggerFactory.getLogger("root");

    private static LogService ascLogService;
    private static Gson gson = new GsonBuilder().create();

    @Resource
    private ThreadPoolTaskExecutor ascLogThreadExecutor;

    private LogService() {

    }

    public static void log(BizzLogInfo logInfo) {
        try {
            final String jsonLog = gson.toJson(logInfo);
            ascLogService.ascLogThreadExecutor.execute(() ->
                LOGGER.info(jsonLog)
            );
        } catch (Exception e) {
            ROOT_LOGGER.error("[bizzLog] asc bizzlog have error.", e);
        }

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        ascLogService = this;
    }

}
