package com.grpc.product.controller;

import com.grpc.product.document.OrderDetail;
import com.grpc.product.exception.RequestNotValidException;
import com.grpc.product.payload.request.OrderRequest;
import com.grpc.product.payload.response.OrderResponse;
import com.grpc.product.service.ElasticSearchService;
import com.grpc.product.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/api")
@AllArgsConstructor
public class OrderController {
    private OrderService orderService;
//    private ElasticSearchService elasticSearchService;

    @PostMapping("/order")
    public ResponseEntity<OrderResponse> saveOrder(@Validated @RequestBody OrderRequest orderRequest) {
        if(orderRequest.getOrderProducts().isEmpty() || orderRequest.getUserId() == 0)  {
            throw new RequestNotValidException("Order creation request is invalid");
        }
        ResponseEntity<OrderResponse> orderResponseResponseEntity = new ResponseEntity<>(orderService.saveOrder(orderRequest), HttpStatus.CREATED);
        return orderResponseResponseEntity;
    }

//    @GetMapping("/orders")
//    public ResponseEntity<List<OrderDetail>> retrieveAllOrders() {
//        ResponseEntity<List<OrderDetail>> orderResponseResponseEntity = new ResponseEntity<>(elasticSearchService.getAll(), HttpStatus.OK);
//        return orderResponseResponseEntity;
//    }
}
