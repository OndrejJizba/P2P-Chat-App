package com.gfa.p2pchatapp.services;

import com.gfa.p2pchatapp.models.Message;

import java.time.LocalDateTime;
import java.util.List;

public interface MessageService {
    Message addMessage (String text) throws Exception;
    List<Message> getAllMessages();
    Message receiveMessage (Long id, String username, String text, LocalDateTime timestamp);
    void saveMessage (Message message);
}
