package com.jhon.rain.utils;

import com.jhon.rain.config.TextMessageHandler;
import org.springframework.web.socket.TextMessage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InputStream;


public class TailLogThread extends Thread {
    private BufferedReader reader;

    public TailLogThread(InputStream in) {
        this.reader = new BufferedReader(new InputStreamReader(in));
    }

    @Override
    public void run() {
        String line;
        try {
            TextMessage message = null;
            while ((line = reader.readLine()) != null) {
                
                message = new TextMessage(line + "<br>");
                SpringContextUtil.getComponent(TextMessageHandler.class).sendMessageToUsers(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
