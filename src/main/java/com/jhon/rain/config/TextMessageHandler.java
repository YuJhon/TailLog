package com.jhon.rain.config;


import com.jifenn.framework.util.CollectionUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


/**
 * <p>功能描述</br> websocket的处理类 </p>
 *
 * @className WebsocketEndPoint
 * @author jiangyu
 * @date 2016年11月11日 下午1:52:55
 * @version v1.0
 */
@Component
public class TextMessageHandler extends TextWebSocketHandler
{

    private static final Map<String,WebSocketSession> users;

    private static final String DEFAULT_WEBSOCKET_USERNAME = "ws_username";

    static{
        users = new HashMap<String, WebSocketSession>();
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        /**
         * 链接成功之后会触发此方法，可在此处对离线消息什么的进行处理
         */
        users.put(session.getId(),session);
        String username = (String) session.getAttributes().get(DEFAULT_WEBSOCKET_USERNAME);
        if (CollectionUtil.isEmpty(username)){
            username = "system";
        }
        System.out.println(username+" connect success ... ");
        session.sendMessage(new TextMessage(" Enbrands2.0 "+username+" WebSSH Link Success"));

    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message)
            throws Exception
    {
        System.out.println("message -> "+message.getPayload());
        super.handleTextMessage(session, message);

    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        super.handleMessage(session, message);
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        if (session.isOpen()){
            session.close();
        }
        System.err.println(exception.getMessage());
        System.out.println("Websocket Connection colsed ...");
        users.remove(session.getId());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        System.out.println("Websocket connection closed ...");
        users.remove(session.getId());
    }

    /**
     * one2one
     * @param username
     * @param textMessage
     */
    public void sendMessageToUser(String username,TextMessage textMessage){
        Iterator<Map.Entry<String,WebSocketSession>> it = userIterator();
        while (it.hasNext()){
            WebSocketSession session = it.next().getValue();
            if (username.equals(session.getAttributes().get(DEFAULT_WEBSOCKET_USERNAME))){
                singleSendMessageHandle(textMessage, session);
            }
        }
    }

    /**
     * 发送单一的消息处理
     * @param textMessage
     * @param session
     */
    private void singleSendMessageHandle(TextMessage textMessage, WebSocketSession session) {
        try {
            if (session.isOpen()){
                session.sendMessage(textMessage);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * broadcast
     * @param textMessage
     */
    public void sendMessageToUsers(TextMessage textMessage){
        Iterator<Map.Entry<String,WebSocketSession>> it = userIterator();
        while (it.hasNext()){
            WebSocketSession session = it.next().getValue();
            singleSendMessageHandle(textMessage, session);
        }
    }

    /**
     *
     * @return
     */
    private Iterator<Map.Entry<String,WebSocketSession>> userIterator(){
        Set<Map.Entry<String,WebSocketSession>> entries = users.entrySet();
        return entries.iterator();
    }
}
