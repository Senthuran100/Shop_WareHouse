package com.grpc.product.payload.request;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderProductRequest {
    @NotBlank
    private long productId;
    @NotBlank
    private int quantity;

}
