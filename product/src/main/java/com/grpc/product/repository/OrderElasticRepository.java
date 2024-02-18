package com.grpc.product.repository;

import com.grpc.product.document.OrderDetail;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


public interface OrderElasticRepository extends ElasticsearchRepository<OrderDetail,String> {
}
