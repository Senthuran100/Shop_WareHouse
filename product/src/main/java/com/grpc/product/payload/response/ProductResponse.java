package com.grpc.product.payload.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProductResponse {
    private Long id;
    private String name;
    private int stock;
    private CategoryResponse category;
    private double price;
}
