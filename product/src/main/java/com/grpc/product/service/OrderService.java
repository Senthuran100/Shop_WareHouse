package com.grpc.product.service;

import com.grpc.product.payload.request.OrderRequest;
import com.grpc.product.payload.response.OrderResponse;

public interface OrderService {

    public OrderResponse saveOrder(OrderRequest orderRequest);
}
