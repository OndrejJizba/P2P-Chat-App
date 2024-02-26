package com.gfa.p2pchatapp.services;

import com.gfa.p2pchatapp.models.Message;
import com.gfa.p2pchatapp.models.User;
import com.gfa.p2pchatapp.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class MessageServiceImp implements MessageService {

    private final MessageRepository messageRepository;
    private final UserService userService;

    @Autowired
    public MessageServiceImp(MessageRepository messageRepository, UserService userService) {
        this.messageRepository = messageRepository;
        this.userService = userService;
        messageRepository.save(new Message("App", "Hi there! Submit your message using the send button!"));
    }

    @Override
    public Message addMessage(String text) throws Exception {
        return messageRepository.save(new Message(text, userService.getUser()));
    }

    @Override
    public List<Message> getAllMessages() {
        return messageRepository.findAll().stream().sorted(Comparator.comparing(Message::getTimestamp)).toList();
    }
}
