package com.gfa.p2pchatapp.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gfa.p2pchatapp.models.DTOs.MessageDTO;
import com.gfa.p2pchatapp.models.Message;
import com.gfa.p2pchatapp.repositories.MessageRepository;
import com.gfa.p2pchatapp.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class ChatControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    UserRepository userRepository;

    private ObjectMapper om;

    @Test
    void receiveMessage() throws Exception {
        int initialAmount = messageRepository.findAll().size();
        MessageDTO message = new MessageDTO(7655482L, "otherUserGitHubUserName", "How you doin'?", 1322018752992L);
        mockMvc.perform(post("/api/message/receive")
                .content(om.writeValueAsString(message))
                .contentType("/application/json"))
                .andExpect(status().is(201))
                .andExpect(jsonPath("$.id", is(7655482)))
                .andExpect(jsonPath("$.username", is("otherUserGitHubUserName")))
                .andExpect(jsonPath("$.text", is("How you doin'?")))
                .andExpect(jsonPath("$.timestamp", is(1322018752992L)));
    }
}