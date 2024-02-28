package com.gfa.p2pchatapp.controllers;

import com.gfa.p2pchatapp.models.DTOs.ClientDTO;
import com.gfa.p2pchatapp.models.DTOs.MessageDTO;
import com.gfa.p2pchatapp.models.DTOs.MessageRequest;
import com.gfa.p2pchatapp.services.LogService;
import com.gfa.p2pchatapp.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    private final MessageService messageService;
    private final LogService logService;

    @Autowired
    public ChatController(MessageService messageService, LogService logService) {
        this.messageService = messageService;
        this.logService = logService;
    }

    @PostMapping("/api/message/receive")
    public ResponseEntity<?> receiveMessage(@RequestBody MessageRequest request){
        MessageDTO message = request.getMessage();
        ClientDTO client = request.getClient();
        if (message.getId() == null || message.getUsername() == null || message.getText() == null || message.getTimestamp() == null || client.getId() == null){
            logService.logError("/api/message/receive", "POST", "message.id=" + message.getId() + " message.username=" + message.getUsername() + " message.text=" + message.getText() + " message.timestamp=" + message.getTimestamp() + " client.id=" + client.getId());
            return ResponseEntity.status(401).body(messageService.receiveMessageError(message, client));
        }
        logService.log("/api/message/receive", "POST", "message.id=" + message.getId() + " message.username=" + message.getUsername() + " message.text=" + message.getText() + " message.timestamp=" + message.getTimestamp() + " client.id=" + client.getId());
        return ResponseEntity.status(201).body(messageService.receiveMessage(message, client));
    }
}
