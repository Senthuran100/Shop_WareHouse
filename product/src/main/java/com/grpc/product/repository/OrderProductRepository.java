package com.grpc.product.repository;

import com.grpc.product.entity.Order;
import com.grpc.product.entity.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductRepository extends JpaRepository<OrderProduct,Long> {
}
