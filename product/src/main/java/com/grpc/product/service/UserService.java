package com.grpc.product.service;

import com.grpc.product.entity.User;
import com.grpc.product.exception.ResourceNotFoundException;
import com.grpc.product.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;

    public User findById(Long id){
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
    }
}
