package com.grpc.product.payload.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
public class OrderProductRequest {
    @NotBlank
    private int quantity;
    @NotBlank
    private Long productId;
}
