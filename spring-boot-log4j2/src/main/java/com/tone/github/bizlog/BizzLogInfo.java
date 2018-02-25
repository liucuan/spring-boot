package com.tone.github.bizlog;


import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by liuzhaoxiang on 2016/2/15.
 */
public class BizzLogInfo {

    /**
     * yyyy-MM-dd HH:mm:ss.SSS
     */
    private String time;

    /**
     */
    private String sysType = "1";
    /**
     * 日志类型 1：业务操作；2:
     */
    private int logType = 1;
    /**
     * 业务动作
     */
    private String action = StringUtils.EMPTY;
    /**
     * 执行时间
     */
    private long duration = 0L;
    /**
     * 是否成功 1：成功；0：失败
     */
    private int status = 1;

    /**
     * 数据源 mysql; redis;elasticsearch
     */
    private String dataSource = StringUtils.EMPTY;

    /**
     * 异常码
     */
    private String errorCode = StringUtils.EMPTY;
    /**
     * 异常
     */
    private String exception = StringUtils.EMPTY;
    /**
     * 描述
     */
    private String desc = StringUtils.EMPTY;

    /**
     * 输入参数
     */
    private Object inputParam;
    /**
     * 返回值
     */
    private Object result;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public BizzLogInfo() {
        time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
    }

    public BizzLogInfo(String action) {
        time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
        this.action = action;
    }

    public String getSysType() {
        return sysType;
    }

    public void setSysType(String sysType) {
        this.sysType = sysType;
    }

    public int getLogType() {
        return logType;
    }

    public void setLogType(int logType) {
        this.logType = logType;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public Object getInputParam() {
        return inputParam == null ? StringUtils.EMPTY : inputParam;
    }

    public void setInputParam(Object inputParam) {
        this.inputParam = inputParam;
    }

    public Object getResult() {
        return result == null ? StringUtils.EMPTY : result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
