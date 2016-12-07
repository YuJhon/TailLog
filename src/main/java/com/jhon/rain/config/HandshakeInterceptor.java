package com.jhon.rain.config;


import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import java.util.Map;


/**
 * <p>功能描述</br> 握手接口 </p>
 * 
 * @className HandshakeInterceptor
 * @author jiangyu
 * @date 2016年11月11日 下午1:54:36
 * @version v1.0
 */
@Component
public class HandshakeInterceptor extends HttpSessionHandshakeInterceptor
{
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes)
        throws Exception
    {
        System.out.println("Before Handshake");
        return super.beforeHandshake(request, response, wsHandler, attributes);
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception ex)
    {
        System.out.println("After Handshake");
        super.afterHandshake(request, response, wsHandler, ex);
    }
}