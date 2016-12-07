package com.jhon.rain.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * <p>websocket配置类</p>
 *
 * @author jiangyu
 * @version v1.0
 * @create 2016-11-18 10:30
 */
@Configuration
@EnableWebMvc
@EnableWebSocket
public class WebSocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer
{
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry)
    {
        webSocketHandlerRegistry.addHandler(textMessageHandler(), "/log").addInterceptors(new HandshakeInterceptor());
        webSocketHandlerRegistry.addHandler(textMessageHandler(), "/websocket/socketjs")
                .addInterceptors(new HandshakeInterceptor())
                .withSockJS();
    }

    @Bean
    public TextMessageHandler textMessageHandler(){
        return new TextMessageHandler();
    }

}
