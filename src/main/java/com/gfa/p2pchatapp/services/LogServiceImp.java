package com.gfa.p2pchatapp.services;

import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class LogServiceImp implements LogService{
    @Override
    public void log(String path, String method, String requestData) {
        String logLevel = System.getenv("CHAT_APP_LOGLEVEL");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String dateTime = dateFormat.format(new Date());
        if (logLevel == null || !logLevel.equals("ERROR")) {
            System.out.println(dateTime + " INFO " + path + " " + method + " " + requestData);
            System.out.println(dateTime + " ERROR " + path + " " + method + " " + requestData);
        }
    }

    @Override
    public void logError(String path, String method, String requestData) {
        String logLevel = System.getenv("CHAT_APP_LOGLEVEL");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String dateTime = dateFormat.format(new Date());
        if (logLevel != null && logLevel.equals("ERROR")) {
            System.out.println(dateTime + " ERROR " + path + " " + method + " " + requestData);
        }
    }
}
