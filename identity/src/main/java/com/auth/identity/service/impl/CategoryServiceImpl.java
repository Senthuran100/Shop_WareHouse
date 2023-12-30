package com.auth.identity.service.impl;

import com.auth.identity.payload.request.CategoryRequest;
import com.auth.identity.payload.response.CategoryResponse;
import com.auth.identity.service.CategoryService;
import com.auth.identity.service.RestClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
@Slf4j
public class CategoryServiceImpl  implements CategoryService {
    private final RestClient restClient;

    @Transactional
    public ResponseEntity<CategoryResponse> invokeCreateCategory(String name) {
       log.info("Calling Category creation service with Category name: "+name);
       CategoryRequest categoryRequest = CategoryRequest.builder()
               .name(name)
               .build();
       ResponseEntity<CategoryResponse> categoryResponse = restClient.postService(categoryRequest, "category", CategoryResponse.class);
       return categoryResponse;
    }
}
