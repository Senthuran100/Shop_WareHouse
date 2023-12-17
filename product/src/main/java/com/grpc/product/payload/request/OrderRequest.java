package com.grpc.product.payload.request;

import com.grpc.product.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@Builder
public class OrderRequest {
    @NotBlank
    private Long userId;
    private List<OrderProductRequest> orderProductList;

}
