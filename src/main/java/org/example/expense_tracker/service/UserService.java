package org.example.expense_tracker.service;

import org.example.expense_tracker.model.User;
import org.example.expense_tracker.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    // Find all users
    public List<User> getAll(){
        return userRepository.findAll();
    }

    // Create User
    public User createUser(User user){
        return userRepository.save(user);
    }

    // Get user by id
    public User getUserById(Long id){
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public List<User> getUserByEmail(String userName){
        return userRepository.findByUsername(userName);
    }
}
