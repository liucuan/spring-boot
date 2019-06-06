package com.tone.socket;

/**
 * @author zhaoxiang.liu
 * @date 2019/6/6
 */
public interface SocketIOService {

    /**
     * 推送的事件
     */
    String PUSH_EVENT = "push_event";

    /**
     * 启动服务
     */
    void start() throws Exception;

    /**
     * 停止服务
     */
    void stop();

    /**
     * 推送信息
     */
    void pushMessageToUser(PushMessage pushMessage);
}
