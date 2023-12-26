package com.auth.identity.service.impl;

import com.auth.identity.payload.response.CategoryResponse;
import com.auth.identity.service.RestClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class CategoryServiceImpl  {
    private final RestClient restClient;

    @Transactional
    public ResponseEntity<CategoryResponse> invokeCreateCategory(String name) {
       ResponseEntity<CategoryResponse> categoryResponse = restClient.postService(name, "category", CategoryResponse.class);
       return categoryResponse;
    }
}
