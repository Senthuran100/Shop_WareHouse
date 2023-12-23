package com.grpc.product.payload.response;

import com.grpc.product.entity.OrderProduct;
import com.grpc.product.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class OrderResponse {
    private Long id;
    private String orderId;
    private LocalDateTime created_date;
    private Long userId;
    private List<OrderProductResponse> orderProductList;
}
