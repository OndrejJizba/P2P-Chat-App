package com.gfa.p2pchatapp.services;

import com.gfa.p2pchatapp.models.DTOs.ClientDTO;
import com.gfa.p2pchatapp.models.DTOs.MessageDTO;
import com.gfa.p2pchatapp.models.DTOs.MessageRequest;
import com.gfa.p2pchatapp.models.Message;
import com.gfa.p2pchatapp.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

@Service
public class MessageServiceImp implements MessageService {

    private final MessageRepository messageRepository;
    private final UserService userService;

    private final RestTemplate restTemplate;

    @Autowired
    public MessageServiceImp(MessageRepository messageRepository, UserService userService, RestTemplate restTemplate) {
        this.messageRepository = messageRepository;
        this.userService = userService;
        this.restTemplate = restTemplate;
        messageRepository.save(new Message("App", "Hi there! Submit your message using the send button!"));
    }

    @Override
    public Message addMessage(String text) throws Exception {
        Message message = new Message(text, userService.getUser());

        String uniqueID = System.getenv("CHAT_APP_UNIQUE_ID");
        String peerAddress = System.getenv("CHAT_APP_PEER_ADDRESS");

        LocalDateTime date = message.getTimestamp();
        Instant instant = date.toInstant(ZoneOffset.UTC);
        Long longDate = instant.toEpochMilli();

        MessageDTO messageDTO = new MessageDTO(message.getId(), message.getUsername(), message.getText(), longDate);
        ClientDTO clientDTO = new ClientDTO(uniqueID);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        MessageRequest messageRequest = new MessageRequest(messageDTO, clientDTO);
        HttpEntity<MessageRequest> request = new HttpEntity<>(messageRequest, headers);
        restTemplate.postForObject(peerAddress + "/api/message/receive", request, MessageRequest.class);

        return messageRepository.save(message);
    }

    @Override
    public List<Message> getAllMessages() {
        return messageRepository.findAll().stream().sorted(Comparator.comparing(Message::getTimestamp)).toList();
    }

    @Override
    public void saveMessage(Message message) {
        messageRepository.save(message);
    }

    @Override
    public HashMap<String, Object> receiveMessage(MessageDTO message, ClientDTO client) {
        Message message1 = new Message();

        Instant instant = Instant.ofEpochMilli(message.getTimestamp());
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.of("UTC"));

        message1.setId(message.getId());
        message1.setUsername(message.getUsername());
        message1.setText(message.getText());
        message1.setTimestamp(localDateTime);
        messageRepository.save(message1);
        HashMap<String, Object> result = new HashMap<>();
        MessageDTO messageDTO = new MessageDTO(message.getId(), message.getUsername(), message.getText(), message.getTimestamp());
        ClientDTO clientDTO = new ClientDTO(client.getId());
        result.put("message", messageDTO);
        result.put("client", clientDTO);
        return result;
    }

    @Override
    public HashMap<String, String> receiveMessageError(MessageDTO message, ClientDTO client) {
        List<String> missingFields = new ArrayList<>();
        StringBuilder error = new StringBuilder("Missing field(s): ");
        if (message.getId() == null){
            missingFields.add("message.id");
        }
        if (message.getUsername() == null){
            missingFields.add("message.username");
        }
        if (message.getText() == null){
            missingFields.add("message.text");
        }
        if (message.getTimestamp() == null){
            missingFields.add("message.timestamp");
        }
        if (client.getId() == null){
            missingFields.add("client.id");
        }
        if (!missingFields.isEmpty()) {
            error.append(String.join(", ", missingFields));
        }
        HashMap<String, String> errorOutput = new HashMap<>();
        errorOutput.put("status", "error");
        errorOutput.put("message", String.valueOf(error));
        return errorOutput;
    }
}
