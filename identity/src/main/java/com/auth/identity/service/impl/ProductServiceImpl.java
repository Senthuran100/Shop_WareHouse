package com.auth.identity.service.impl;

import com.auth.identity.payload.request.ProductRequest;
import com.auth.identity.payload.response.ProductResponse;
import com.auth.identity.service.ProductService;
import com.auth.identity.service.RestClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final RestClient restClient;

    @Override
    public ResponseEntity<ProductResponse> invokeCreateProduct(String name, double price,int stock,long categoryId,long userId) {
        log.info("Calling Product creation service with name: "+name);
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
