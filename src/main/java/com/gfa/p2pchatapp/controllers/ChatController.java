package com.gfa.p2pchatapp.controllers;

import com.gfa.p2pchatapp.models.Message;
import com.gfa.p2pchatapp.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

@RestController
public class ChatController {

    private final MessageService messageService;

    @Autowired
    public ChatController(MessageService messageService) {
        this.messageService = messageService;
    }

    public void log(String path, String method, String requestData) {
        String logLevel = System.getenv("CHAT_APP_LOGLEVEL");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String dateTime = dateFormat.format(new Date());
        if (logLevel == null || !logLevel.equals("ERROR")) {
            System.out.println(dateTime + " INFO " + path + " " + method + " " + requestData);
        } else if (logLevel.equals("ERROR")) {
            System.out.println(dateTime + " ERROR " + path + " " + method + " " + requestData);
        }
    }

    @PostMapping("/api/message/receive")
    public ResponseEntity<?> receiveMessage(@RequestBody Message message){
        HashMap<String, Object> input = new HashMap<>();
        input.put("message", messageService.receiveMessage(message.getId(), message.getUsername(), message.getText(), message.getTimestamp()));
        input.put("client", "\"id\": \"" + message.getUsername() + "\"");
        return ResponseEntity.status(201).body(input);
    }
}
