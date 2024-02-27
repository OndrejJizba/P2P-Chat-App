package com.gfa.p2pchatapp.services;

import com.gfa.p2pchatapp.models.DTOs.ClientDTO;
import com.gfa.p2pchatapp.models.DTOs.MessageDTO;
import com.gfa.p2pchatapp.models.Message;

import java.util.HashMap;
import java.util.List;

public interface MessageService {
    Message addMessage (String text) throws Exception;
    List<Message> getAllMessages();
    void saveMessage (Message message);

    HashMap<String, Object> receiveMessage(MessageDTO message, ClientDTO client);
    HashMap<String, String> receiveMessageError(MessageDTO message, ClientDTO client);
}
