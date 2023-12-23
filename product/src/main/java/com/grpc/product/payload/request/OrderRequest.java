package com.grpc.product.payload.request;

import com.grpc.product.entity.User;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {
    private long userId;
    private List<OrderProductRequest> orderProductList;

}
