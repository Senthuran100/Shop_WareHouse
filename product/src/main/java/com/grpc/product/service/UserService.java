package com.grpc.product.service;

import com.grpc.product.entity.User;

public interface UserService {
    public User findById(Long id);
}
