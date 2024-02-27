package com.gfa.p2pchatapp.models.DTOs;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MessageRequest {
    private MessageDTO message;
    private ClientDTO client;

    public MessageRequest(MessageDTO message, ClientDTO client) {
        this.message = message;
        this.client = client;
    }
}
