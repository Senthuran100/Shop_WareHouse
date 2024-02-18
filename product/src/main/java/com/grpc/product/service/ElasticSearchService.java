package com.grpc.product.service;

import com.grpc.product.document.OrderDetail;
import com.grpc.product.entity.Order;

import java.util.List;

public interface ElasticSearchService {

    public String saveOrder(Order order);
    public List<OrderDetail> getAll();

}
