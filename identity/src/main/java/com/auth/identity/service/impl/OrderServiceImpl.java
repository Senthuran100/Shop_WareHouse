package com.auth.identity.service.impl;

import com.auth.identity.payload.request.OrderRequest;
import com.auth.identity.payload.response.OrderResponse;
import com.auth.identity.service.OrderService;
import com.auth.identity.service.RestClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final RestClient restClient;

    @Override
    public ResponseEntity<OrderResponse> invokeCreateOrder(OrderRequest orderRequest) {
        ResponseEntity<OrderResponse> orderResponse = restClient.postService(orderRequest, "order", OrderResponse.class);
        return orderResponse;
    }
}
