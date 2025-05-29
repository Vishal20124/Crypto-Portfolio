package com.bridgelabz.service;

import com.bridgelabz.entity.User;
import com.bridgelabz.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User assignRole(String username, String newRole) {
        User user = userRepository.findByName(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String currentRoles = user.getRoles();
        if (currentRoles == null || currentRoles.isEmpty()) {
            user.setRoles(newRole);
        } else if (!currentRoles.contains(newRole)) {
            user.setRoles(currentRoles + "," + newRole);
        }

        return userRepository.save(user);
    }

    public User getUserByName(String username) {
        return userRepository.findByName(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}

