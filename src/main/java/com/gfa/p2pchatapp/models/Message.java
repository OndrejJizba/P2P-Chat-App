package com.gfa.p2pchatapp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
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
    private User user;

    public Message(String text, User user) {
        this.username = user.getName();
        this.text = text;
        this.timestamp = getTimestamp();
        this.id = generateId();
        this.user = user;
    }

    public Message(String username, String text) {
        this.username = username;
        this.text = text;
        this.id = generateId();
    }

    private Long generateId() {
        Random random = new Random();
        return Math.abs(random.nextLong()) % (9999999 - 1000000 + 1) + 1000000;
    }
}
