package com.auth.identity.service;

import com.auth.identity.payload.request.OrderRequest;
import com.auth.identity.payload.response.OrderResponse;
import org.springframework.http.ResponseEntity;

public interface OrderService {
    public ResponseEntity<OrderResponse> invokeCreateOrder(OrderRequest orderRequest);
}
