package com.jhon.rain.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;


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
