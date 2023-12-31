package com.grpc.product.service.impl;

import com.grpc.product.entity.Category;
import com.grpc.product.payload.request.CategoryRequest;
import com.grpc.product.payload.response.CategoryResponse;
import com.grpc.product.repository.CategoryRepository;
import com.grpc.product.service.CategoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@AllArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    public Category findById(Long id) {
        log.info("Get Category with Id: "+id);
        return categoryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Category not found with id " + id));
    }

    public CategoryResponse saveCategory(CategoryRequest categoryRequest) {
        Category category = Category.builder().name(categoryRequest.getName()).build();
        try {
            log.info("Category Creation with name: "+ categoryRequest.getName());
            Category categoryResponse = categoryRepository.save(category);
            return CategoryResponse.builder().id(categoryResponse.getId()).name(categoryResponse.getName()).build();
        } catch (Exception e) {
            log.info("Category Creation exception encountered");
            throw new RuntimeException(e);
        }
    }
}
