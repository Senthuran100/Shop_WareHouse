package com.grpc.product.service;

import com.grpc.product.entity.Product;
import com.grpc.product.model.PageSettings;
import com.grpc.product.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class ProductService {

    private ProductRepository productRepository;

    public Page<Product> getProductPage(@NonNull PageSettings pageSettings) {
        Sort productSort = pageSettings.buildSort();
        Pageable productPage = PageRequest.of(pageSettings.getPage(),pageSettings.getElementPerPage(),productSort);

        return productRepository.findAll(productPage);
    }
}
