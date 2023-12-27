package com.auth.identity.service.impl;

import com.auth.identity.payload.request.ProductRequest;
import com.auth.identity.payload.response.ProductResponse;
import com.auth.identity.service.ProductService;
import com.auth.identity.service.RestClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final RestClient restClient;

    @Override
    public ResponseEntity<ProductResponse> invokeCreateProduct(String name, double price,int stock,long categoryId,long userId) {
        ProductRequest productRequest = ProductRequest.builder()
                .name(name)
                .price(price)
                .categoryId(categoryId)
                .stock(stock)
                .userId(userId)
                .build();
        ResponseEntity<ProductResponse> productResponse = restClient.postService(productRequest, "product", ProductResponse.class);
        return productResponse;
    }
}
