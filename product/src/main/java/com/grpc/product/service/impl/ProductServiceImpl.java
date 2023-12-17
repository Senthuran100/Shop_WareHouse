package com.grpc.product.service.impl;

import com.grpc.product.entity.Category;
import com.grpc.product.entity.Product;
import com.grpc.product.entity.User;
import com.grpc.product.exception.ResourceNotFoundException;
import com.grpc.product.model.PageSettings;
import com.grpc.product.payload.request.ProductRequest;
import com.grpc.product.payload.response.CategoryResponse;
import com.grpc.product.payload.response.ProductResponse;
import com.grpc.product.repository.CategoryRepository;
import com.grpc.product.repository.ProductRepository;
import com.grpc.product.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    private UserServiceImpl userService;

    public Page<Product> getProductPage(@NonNull PageSettings pageSettings) {
        Sort productSort = pageSettings.buildSort();
        Pageable productPage = PageRequest.of(pageSettings.getPage(), pageSettings.getElementPerPage(), productSort);

        return productRepository.findAll(productPage);
    }

    public ProductResponse saveProduct(ProductRequest productRequest) {
        String productCode = UUID.randomUUID().toString();
        try {
            Category category = categoryRepository.findById(productRequest.getCategoryId()).orElseThrow(() -> new ResourceNotFoundException("Category is not found with id : " + productRequest.getCategoryId()));
            User user = userService.findById(productRequest.getUserId());
            Product product = Product.builder()
                    .category(category)
                    .productCode(productCode)
                    .stock(productRequest.getStock())
                    .name(productRequest.getName())
                    .price(productRequest.getPrice())
                    .created_date(LocalDateTime.now())
                    .user(user)
                    .build();
            Product savedProduct = productRepository.save(product);
            return ProductResponse.builder()
                    .id(savedProduct.getId())
                    .category(CategoryResponse.builder()
                            .id(savedProduct.getCategory().getId())
                            .name(savedProduct.getCategory().getName())
                            .build())
                    .name(savedProduct.getName())
                    .price(savedProduct.getPrice())
                    .stock(savedProduct.getStock())
                    .build();
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }

    }
}
