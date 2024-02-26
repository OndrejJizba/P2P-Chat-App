package com.gfa.p2pchatapp.repositories;

import com.gfa.p2pchatapp.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
