package com.jhon.rain.utils;

import com.jhon.rain.config.TextMessageHandler;
import org.springframework.web.socket.TextMessage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InputStream;

/**
 * <p>查询日志的线程类</p>
 *
 * @author jiangyu
 * @version v1.0
 * @className TailLogThread
 * @create 2016-12-07 20:30
 */
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
                // 将实时日志通过WebSocket发送给客户端，给每一行添加一个HTML换行
                message = new TextMessage(line + "<br>");
                SpringContextUtil.getComponent(TextMessageHandler.class).sendMessageToUsers(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
