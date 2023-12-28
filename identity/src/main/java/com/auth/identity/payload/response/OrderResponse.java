package com.auth.identity.payload.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
    private Long id;
    private String orderId;
    private LocalDateTime created_date;
    private Long userId;
    private List<OrderProductResponse> orderProductList;
}
