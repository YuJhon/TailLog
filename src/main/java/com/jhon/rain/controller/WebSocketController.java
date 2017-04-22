package com.jhon.rain.controller;

import com.jhon.rain.utils.TailLogThread;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.InputStream;


@Controller
@RequestMapping("/debug")
public class WebSocketController
{
    private Process process;

    private InputStream inputStream;

    @RequestMapping(value = "/webssh.do", method = RequestMethod.GET)
    public String index()
    {
        return "webssh";
    }

    @RequestMapping(value = "/socketDemo.do", method = RequestMethod.GET)
    @ResponseBody
    public String queryMemberInfo_test()
    {
        try
        {
            String command = "tail -f /home/javaApp/tomcat-app-b/logs/catalina.out";
            // String command = "cmd /c ipconfig /all";
            process = Runtime.getRuntime().exec(command);
            inputStream = process.getInputStream();
            TailLogThread thread = new TailLogThread(inputStream);
            thread.start();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            try
            {
                if (inputStream != null) inputStream.close();
            }
            catch (Exception e1)
            {
                e1.printStackTrace();
            }
            if (process != null) process.destroy();
        }
        return "";
    }
}
