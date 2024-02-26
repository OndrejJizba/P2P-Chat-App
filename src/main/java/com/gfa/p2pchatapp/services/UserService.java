package com.gfa.p2pchatapp.services;

import com.gfa.p2pchatapp.models.User;

import java.util.List;

public interface UserService {
    User addUser(User user);
    List<User> getAllUsers();
    User changeName (Long id, String name) throws Exception;
    String getName (Long id) throws Exception;
}
