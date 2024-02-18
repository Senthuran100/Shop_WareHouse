package com.grpc.product.document;

import com.grpc.product.entity.OrderProduct;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Document(indexName = "orderindex")
@Builder
public class OrderDetail {

    @Id
    private String id;

    @Field(type = FieldType.Text, name = "orderId")
    private String orderId;

    @Field(type = FieldType.Date, name = "createdDate")
    private LocalDateTime createdDate;

    @Field(type = FieldType.Long, name = "userId")
    private Long userId;

    @Field(type = FieldType.Object, name = "orderProductList")
    private List<OrderProduct> orderProductList;

}
