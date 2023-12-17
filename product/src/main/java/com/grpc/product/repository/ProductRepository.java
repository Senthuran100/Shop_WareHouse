package com.grpc.product.repository;

import com.grpc.product.entity.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {

    @Query(value = "Select p from Product p where p.id In :productIds")
    public List<Product> findByProductIdIn(List<Long> productIds);
}
