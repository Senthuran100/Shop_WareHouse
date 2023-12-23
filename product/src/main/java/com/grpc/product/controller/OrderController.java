package com.grpc.product.controller;

import com.grpc.product.payload.request.OrderRequest;
import com.grpc.product.payload.response.OrderResponse;
import com.grpc.product.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/api")
@AllArgsConstructor
public class OrderController {
    private OrderService orderService;

    @PostMapping("/order")
    public ResponseEntity<OrderResponse> saveOrder(@Validated @RequestBody OrderRequest orderRequest) {
        ResponseEntity<OrderResponse> orderResponseResponseEntity = new ResponseEntity<>(orderService.saveOrder(orderRequest), HttpStatus.CREATED);
        return orderResponseResponseEntity;
    }
}
