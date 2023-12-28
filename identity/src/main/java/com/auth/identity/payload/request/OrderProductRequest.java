package com.auth.identity.payload.request;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderProductRequest {
    @NotBlank
    private int quantity;
    @NotBlank
    private long productId;
}
