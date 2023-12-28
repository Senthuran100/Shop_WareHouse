package com.auth.identity.payload.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderProductResponse {
    private Long id;
    private int quantity;
    private Long productId;
}
