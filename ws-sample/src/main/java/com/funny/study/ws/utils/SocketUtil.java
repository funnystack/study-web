package com.funny.study.ws.utils;

import com.funny.study.ws.pojo.SocketUser;
import org.springframework.web.socket.WebSocketSession;

public class SocketUtil {

    public static SocketUser getSocketUser(WebSocketSession session){
        return new SocketUser();
    };
}
