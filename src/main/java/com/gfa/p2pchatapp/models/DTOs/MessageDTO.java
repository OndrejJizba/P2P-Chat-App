package com.gfa.p2pchatapp.models.DTOs;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class MessageDTO {
    private Long id;
    private String username;
    private String text;
    private Long timestamp;

    public MessageDTO(Long id, String username, String text, Long timestamp) {
        this.id = id;
        this.username = username;
        this.text = text;
        this.timestamp = timestamp;
    }
}
