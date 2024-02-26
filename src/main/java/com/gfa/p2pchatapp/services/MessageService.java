package com.gfa.p2pchatapp.services;

import com.gfa.p2pchatapp.models.Message;
import com.gfa.p2pchatapp.models.User;

import java.util.List;

public interface MessageService {
    Message addMessage (String text, User user) throws Exception;
    List<Message> getAllMessages();
}
