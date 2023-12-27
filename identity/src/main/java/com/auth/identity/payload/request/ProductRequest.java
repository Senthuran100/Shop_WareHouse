package com.auth.identity.payload.request;

import lombok.*;

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
