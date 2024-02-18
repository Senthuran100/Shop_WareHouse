package com.grpc.product.service.impl;

import com.grpc.product.document.OrderDetail;
import com.grpc.product.entity.Order;
import com.grpc.product.repository.OrderElasticRepository;
import com.grpc.product.service.ElasticSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ElasticSearchServiceImpl implements ElasticSearchService {

    private final OrderElasticRepository orderElasticRepository;

    @Override
    public String saveOrder(Order order) {
      OrderDetail orderDetail = OrderDetail.builder()
              .orderId(order.getOrderId())
              .orderProductList(order.getOrderProductList())
              .userId(order.getUser().getId())
              .createdDate(order.getCreated_date())
              .build();
      OrderDetail savedOrderDetail = orderElasticRepository.save(orderDetail);
      return savedOrderDetail.getId();
    }

    public List<OrderDetail> getAll() {
        return (List<OrderDetail>) orderElasticRepository.findAll();
    }
}
