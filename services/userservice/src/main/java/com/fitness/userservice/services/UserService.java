package com.fitness.userservice.services;

import com.fitness.userservice.dto.RegisterRequest;
import com.fitness.userservice.dto.UserResponse;
import com.fitness.userservice.models.User;
import com.fitness.userservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository repository;

    public UserResponse register(RegisterRequest request) {

        if(repository.existsByEmail(request.getEmail())){
            User existingUser=repository.findByEmail(request.getEmail());
            UserResponse response=new UserResponse();
            response.setId(existingUser.getId());
            response.setEmail(existingUser.getEmail());
            response.setFirstName(existingUser.getFirstName());
            response.setLastName(existingUser.getLastName());
            response.setUpdatedAt(existingUser.getUpdatedAt());
            response.setCreatedAt(existingUser.getCreatedAt());
        }

        User user=new User();
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setKeycloakId(request.getKeycloakId());
        user.setLastName(request.getLastName());
        user.setPassword(request.getPassword());

        User savedUser=repository.save(user);
        UserResponse response=new UserResponse();
        response.setId(savedUser.getId());
        response.setEmail(savedUser.getEmail());
        response.setKeycloakId(savedUser.getKeycloakId());
        response.setFirstName(savedUser.getFirstName());
        response.setLastName(savedUser.getLastName());
        response.setUpdatedAt(savedUser.getUpdatedAt());
        response.setCreatedAt(savedUser.getCreatedAt());

        return response;
    }

    public UserResponse getUserProfile(String userId) {
        User user=repository.findById(userId)
                .orElseThrow(()->new RuntimeException("User not Found"));
        UserResponse response=new UserResponse();
        response.setId(user.getId());
        response.setEmail(user.getEmail());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setUpdatedAt(user.getUpdatedAt());
        response.setCreatedAt(user.getCreatedAt());

        return response;
    }

    public Boolean existByUserId(String userId) {
        log.info("Calling user service for {}",userId);
        return repository.existsByKeycloakId(userId);

    }
}
