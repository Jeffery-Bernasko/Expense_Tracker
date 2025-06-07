package org.example.expense_tracker.service;

import org.example.expense_tracker.dto.UserRegistrationDTO;
import org.example.expense_tracker.model.Role;
import org.example.expense_tracker.model.User;
import org.example.expense_tracker.repository.RoleRepository;
import org.example.expense_tracker.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.List;
import java.util.Set;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    // Find all users
    public List<User> getAll(){
        return userRepository.findAll();
    }

    // Create User
    public User createUser(User user){
        return userRepository.save(user);
    }

    // Register User using Registration DTO
    public void registerUSer(UserRegistrationDTO dto){
        if(userRepository.findByUsername(dto.getUsername()) != null){
            throw new RuntimeException("Username already exist");
        }

        // Set the Role for user
        Role userRole = new Role("ROLE_USER");
        roleRepository.save(userRole);

        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setRoles(Set.of(userRole));

        userRepository.save(user);
    }

    // Get user by id
    public User getUserById(Long id){
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User getUserByEmail(String userName){
        return userRepository.findByUsername(userName);
    }
}
