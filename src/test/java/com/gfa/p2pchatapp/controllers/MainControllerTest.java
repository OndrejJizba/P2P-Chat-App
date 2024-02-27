package com.gfa.p2pchatapp.controllers;

import com.gfa.p2pchatapp.repositories.MessageRepository;
import com.gfa.p2pchatapp.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class MainControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    UserRepository userRepository;

}