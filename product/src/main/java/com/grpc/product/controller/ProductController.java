package com.grpc.product.controller;

import com.grpc.product.payload.request.CategoryRequest;
import com.grpc.product.payload.response.CategoryResponse;
import com.grpc.product.service.CategoryService;
import com.grpc.product.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ProductController {

    private CategoryService categoryService;

    private ProductService productService;

    @PostMapping("/category")
    public ResponseEntity<CategoryResponse> saveCategory(CategoryRequest categoryRequest) {
        ResponseEntity<CategoryResponse> categoryResponseResponseEntity = new ResponseEntity<>(categoryService.saveCategory(categoryRequest),HttpStatus.CREATED);
        return categoryResponseResponseEntity;
    }
}
