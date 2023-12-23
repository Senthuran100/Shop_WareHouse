package com.grpc.product.payload.response;

import com.grpc.product.entity.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OrderProductResponse {
    private Long id;
    private int quantity;
    private Long productId;
}
