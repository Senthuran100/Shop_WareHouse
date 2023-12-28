package com.auth.identity.payload.request;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {
    private long userId;
    private List<OrderProductRequest> orderProducts;
}
