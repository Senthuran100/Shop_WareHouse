package com.auth.identity.service;

import com.auth.identity.payload.response.ProductResponse;
import org.springframework.http.ResponseEntity;

public interface ProductService {
    public ResponseEntity<ProductResponse> invokeCreateProduct(String name, double price,int stock,long categoryId,long userId);
}
