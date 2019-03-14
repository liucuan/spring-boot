package com.tone.github.bizlog;

/**
 * Created by liuzhaoxiang on 2016/3/11.
 */
public class BizzLogBuilder {
    private BizzLogInfo bizzLogInfo = new BizzLogInfo();
    private long startTime = System.currentTimeMillis();


    public BizzLogBuilder action(String action) {
        bizzLogInfo.setAction(action);
        return this;
    }

    public BizzLogBuilder startTime(long startTime) {
        this.startTime = startTime;
        return this;
    }

    public BizzLogBuilder sysType(String sysType) {
        bizzLogInfo.setSysType(sysType);
        return this;
    }

    public BizzLogBuilder logType(int logType) {
        bizzLogInfo.setLogType(logType);
        return this;
    }

    public BizzLogBuilder duration(long duration) {
        bizzLogInfo.setDuration(duration);
        return this;
    }

    public BizzLogBuilder status(int status) {
        bizzLogInfo.setStatus(status);
        return this;
    }

    public BizzLogBuilder dataSource(String dataSource) {
        bizzLogInfo.setDataSource(dataSource);
        return this;
    }

    public BizzLogBuilder errorCode(String errorCode) {
        bizzLogInfo.setErrorCode(errorCode);
        return this;
    }

    public BizzLogBuilder exception(String exception) {
        bizzLogInfo.setException(exception);
        return this;
    }

    public BizzLogBuilder desc(String desc) {
        bizzLogInfo.setDesc(desc);
        return this;
    }

    public BizzLogBuilder inputParam(Object inputParam) {
        bizzLogInfo.setInputParam(inputParam);
        return this;
    }

    public BizzLogBuilder result(Object result) {
        bizzLogInfo.setResult(result);
        return this;
    }

    public void doLog() {
        bizzLogInfo.setDuration(System.currentTimeMillis() - startTime);
        LogService.log(this.bizzLogInfo);
    }

    public BizzLogInfo bizzLogInfo() {
        return this.bizzLogInfo;
    }
}
