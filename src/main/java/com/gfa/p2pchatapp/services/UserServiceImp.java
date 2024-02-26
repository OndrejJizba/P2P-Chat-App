package com.gfa.p2pchatapp.services;

import com.gfa.p2pchatapp.models.User;
import com.gfa.p2pchatapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public User changeName(Long id, String name) throws Exception {
        User user = userRepository.findById(id).orElseThrow(() -> new Exception("User not found."));
        user.setName(name);
        return userRepository.save(user);
    }

    @Override
    public String getName(Long id) throws Exception {
        User user = userRepository.findById(id).orElseThrow(() -> new Exception("User not found."));
        return user.getName();
    }
}
