package com.grpc.product.payload.request;

import lombok.*;

import javax.validation.constraints.NotBlank;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequest {
    private String name;
    private double price;
    private int stock;
    private long categoryId;
    private long userId;
}
