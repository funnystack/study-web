package com.funny.study.ws.config;

import com.funny.study.ws.interceptor.WsTestSocketHandler;
import com.funny.study.ws.interceptor.CommonSocketInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import javax.annotation.Resource;

/**
 * @author funnystack
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Resource
    private WsTestSocketHandler commonSocketHandler;

    @Resource
    private CommonSocketInterceptor commonSocketInterceptor;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(commonSocketHandler, "/wstest")
                .addInterceptors(commonSocketInterceptor)
                .setAllowedOrigins("*");
    }
}
