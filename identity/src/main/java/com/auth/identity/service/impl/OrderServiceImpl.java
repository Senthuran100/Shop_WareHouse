package com.auth.identity.service.impl;

import com.auth.identity.payload.request.OrderRequest;
import com.auth.identity.payload.response.OrderResponse;
import com.auth.identity.service.OrderService;
import com.auth.identity.service.RestClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final RestClient restClient;

    @Override
    public ResponseEntity<OrderResponse> invokeCreateOrder(OrderRequest orderRequest) {
        log.info("Calling Order creation service with user Id: "+orderRequest.getUserId());
        ResponseEntity<OrderResponse> orderResponse = restClient.postService(orderRequest, "order", OrderResponse.class);
        return orderResponse;
    }
}
