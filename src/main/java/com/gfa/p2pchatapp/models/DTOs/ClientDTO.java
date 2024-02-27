package com.gfa.p2pchatapp.models.DTOs;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ClientDTO {
    private String id;

    public ClientDTO(String id) {
        this.id = id;
    }
}
