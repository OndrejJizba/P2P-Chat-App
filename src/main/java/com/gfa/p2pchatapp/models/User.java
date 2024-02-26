package com.gfa.p2pchatapp.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany (cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    private List<Message> messages;

    public User(String name) {
        this.name = name;
    }
}
