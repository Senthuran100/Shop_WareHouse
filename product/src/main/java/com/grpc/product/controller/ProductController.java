package com.grpc.product.controller;

import com.grpc.product.payload.request.CategoryRequest;
import com.grpc.product.payload.request.ProductRequest;
import com.grpc.product.payload.response.CategoryResponse;
import com.grpc.product.payload.response.ProductResponse;
import com.grpc.product.service.impl.CategoryServiceImpl;
import com.grpc.product.service.impl.ProductServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("v1/api")
@AllArgsConstructor
public class ProductController {

    private CategoryServiceImpl categoryService;
    private ProductServiceImpl productService;

    @PostMapping("/category")
    public ResponseEntity<CategoryResponse> saveCategory(@Validated @RequestBody  CategoryRequest categoryRequest) {
        ResponseEntity<CategoryResponse> categoryResponseResponseEntity = new ResponseEntity<>(categoryService.saveCategory(categoryRequest),HttpStatus.CREATED);
        return categoryResponseResponseEntity;
    }

    @PostMapping("/product")
    public ResponseEntity<ProductResponse> saveProduct(@Validated @RequestBody ProductRequest productRequest) {
       ResponseEntity<ProductResponse> productResponseResponseEntity = new ResponseEntity<>(productService.saveProduct(productRequest), HttpStatus.CREATED);
       return productResponseResponseEntity;
    }
}
