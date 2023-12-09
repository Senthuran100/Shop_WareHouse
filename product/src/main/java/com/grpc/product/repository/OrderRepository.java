package com.grpc.product.repository;

import com.grpc.product.entity.Category;
import com.grpc.product.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
