package com.funny.study.ws.pojo;

import java.io.Serializable;

public class SocketUser implements Serializable {

    private String token;//用户登录唯一验证
    private String sessionId;//websocketID

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
