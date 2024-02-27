package com.gfa.p2pchatapp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Random;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Message {

    private String username;
    private String text;
    @CreationTimestamp
    private LocalDateTime timestamp;
    @Id
    private Long id;
    @ManyToOne
    @JsonIgnore
    private User user;

    public Message(String text, User user) {
        this.username = user.getName();
        this.text = text;
        this.id = generateId();
        this.user = user;
    }

    public Message(String username, String text) {
        this.username = username;
        this.text = text;
        this.id = generateId();
    }

    public Message(Long id, String username, String text, LocalDateTime timestamp) {
        this.username = username;
        this.text = text;
        this.timestamp = timestamp;
        this.id = id;
    }

    private Long generateId() {
        Random random = new Random();
        return Math.abs(random.nextLong()) % (9999999 - 1000000 + 1) + 1000000;
    }
}
