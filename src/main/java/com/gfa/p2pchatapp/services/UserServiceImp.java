package com.gfa.p2pchatapp.services;

import com.gfa.p2pchatapp.models.User;
import com.gfa.p2pchatapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService{

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User changeName(String name) throws Exception {
        User user = userRepository.findById(1L).orElseThrow(() -> new Exception("User not found."));
        user.setName(name);
        return userRepository.save(user);
    }

    @Override
    public String getName() throws Exception {
        User user = userRepository.findById(1L).orElseThrow(() -> new Exception("User not found."));
        return user.getName();
    }

    @Override
    public User getUser() throws Exception {
        return userRepository.findById(1L).orElseThrow(() -> new Exception("User not found."));
    }
}
