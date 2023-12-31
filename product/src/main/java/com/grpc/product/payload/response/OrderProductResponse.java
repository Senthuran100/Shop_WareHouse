package com.grpc.product.payload.response;

import com.grpc.product.entity.Product;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderProductResponse {
    private Long id;
    private int quantity;
    private Long productId;
}
