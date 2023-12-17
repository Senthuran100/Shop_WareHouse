package com.grpc.product.service;

import com.grpc.product.entity.Product;
import com.grpc.product.model.PageSettings;
import com.grpc.product.payload.request.ProductRequest;
import com.grpc.product.payload.response.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.lang.NonNull;

public interface ProductService {

    public Page<Product> getProductPage(@NonNull PageSettings pageSettings);

    public ProductResponse saveProduct(ProductRequest productRequest);
}
