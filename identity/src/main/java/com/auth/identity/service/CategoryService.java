package com.auth.identity.service;

import com.auth.identity.payload.response.CategoryResponse;
import org.springframework.http.ResponseEntity;

public interface CategoryService {

    public ResponseEntity<CategoryResponse> invokeCreateCategory(String name);
}
