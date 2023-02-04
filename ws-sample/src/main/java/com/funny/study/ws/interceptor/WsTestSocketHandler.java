package com.funny.study.ws.interceptor;

import com.funny.study.ws.pojo.SocketUser;
import com.funny.study.ws.utils.SocketUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class WsTestSocketHandler extends TextWebSocketHandler {

    private static final Logger LOG = LoggerFactory.getLogger(WsTestSocketHandler.class);

    /**
     * 创建连接
     *
     * @param session 用户session
     * @throws Exception
     * @version v1.0
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        System.out.println("afterConnectionEstablished");
        try {
            //获取用户信息
            SocketUser socketUser = SocketUtil.getSocketUser(session);
            if (null == socketUser) {
                return;
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * 关闭链接
     *
     * @param session 用户session
     * @param status  状态
     * @throws Exception
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session,
                                      CloseStatus status) {
        System.out.println("afterConnectionClosed");
        try {
            //获取用户信息
            SocketUser socketUser = SocketUtil.getSocketUser(session);
            if (null == socketUser) {
                return;
            }
            //删除缓存
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * 处理消息
     *
     * @param session 用户session
     * @param message message消息
     */
    @Override
    protected void handleTextMessage(WebSocketSession session,
                                     TextMessage message) {
        try {
            //获取用户信息
            SocketUser socketUser = SocketUtil.getSocketUser(session);
            if (null == socketUser) {
                return;
            }
            String str = message.getPayload();
            if (StringUtils.isBlank(str)) {
                //空消息不做操作
                return;
            }
            System.out.println("received message:"+str);

            session.sendMessage(new TextMessage("echo"+message.getPayload()));
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

}
