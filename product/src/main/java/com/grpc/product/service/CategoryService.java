package com.grpc.product.service;

import com.grpc.product.entity.Category;
import com.grpc.product.payload.request.CategoryRequest;
import com.grpc.product.payload.response.CategoryResponse;

public interface CategoryService {
    public Category findById(Long id);

    public CategoryResponse saveCategory(CategoryRequest categoryRequest);
}
