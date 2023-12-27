package com.auth.identity.payload.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
    private Long id;
    private String name;
    private int stock;
    private CategoryResponse category;
    private double price;
}
