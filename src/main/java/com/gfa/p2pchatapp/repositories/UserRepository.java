package com.gfa.p2pchatapp.repositories;

import com.gfa.p2pchatapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
