package com.grpc.product.service.impl;

import com.grpc.product.entity.User;
import com.grpc.product.exception.ResourceNotFoundException;
import com.grpc.product.repository.UserRepository;
import com.grpc.product.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    public User findById(Long id){
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
    }
}
