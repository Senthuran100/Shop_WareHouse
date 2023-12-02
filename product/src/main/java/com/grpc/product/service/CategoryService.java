package com.grpc.product.service;

import com.grpc.product.entity.Category;
import com.grpc.product.entity.User;
import com.grpc.product.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@AllArgsConstructor
public class CategoryService {
    private CategoryRepository categoryRepository;
    public Category findById(Long id){
        return categoryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Category not found with id " + id));
    }
}
