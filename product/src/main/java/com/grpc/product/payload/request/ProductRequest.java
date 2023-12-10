package com.grpc.product.payload.request;

import lombok.*;

import javax.validation.constraints.NotBlank;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequest {
    @NotBlank
    private String name;
    @NotBlank
    private double price;
    @NotBlank
    private int stock;
    @NotBlank
    private long categoryId;
    @NotBlank
    private long userId;
}
