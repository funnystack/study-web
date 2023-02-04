
package com.funny.study.ws.interceptor;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

/**
 */
@Component
public class CommonSocketInterceptor implements HandshakeInterceptor {

    /**
     * 握手前
     *
     * @param request
     * @param response
     * @param wsHandler
     * @param attributes
     * @return
     * @throws Exception
     */
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        ServletServerHttpRequest serverHttpRequest = (ServletServerHttpRequest) request;
        ServletServerHttpResponse serverHttpResponse = (ServletServerHttpResponse) response;
        String authorization = serverHttpRequest.getServletRequest().getHeader("Sec-WebSocket-Protocol");
        //这里对获取到的 authorization 授权码进行业务校验如 jwt 校验
        System.out.println("beforeHandshake,authorization="+authorization);
        //...
        //在后端握手时设置一下请求头（Sec-WebSocket-Protocol），前端发来什么授权值，这里就设置什么值，不设置会报错导致建立连接成功后立即被关闭
        serverHttpResponse.getServletResponse().setHeader("Sec-WebSocket-Protocol", authorization);
        return true;
    }

    /**
     * 握手后
     *
     * @param request
     * @param response
     * @param wsHandler
     * @param exception
     */
    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {
        System.out.println("afterHandshake");
    }

}
