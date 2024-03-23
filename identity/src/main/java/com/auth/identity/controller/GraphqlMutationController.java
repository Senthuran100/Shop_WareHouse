package com.auth.identity.controller;

import com.auth.identity.payload.request.OrderRequest;
import com.auth.identity.payload.response.CategoryResponse;
import com.auth.identity.payload.response.OrderResponse;
import com.auth.identity.payload.response.ProductResponse;
import com.auth.identity.service.CategoryService;
import com.auth.identity.service.OrderService;
import com.auth.identity.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class GraphqlMutationController {

    private final CategoryService categoryService;
    private final ProductService productService;
    private final OrderService orderService;

    @MutationMapping
    @PreAuthorize("hasRole('ADMIN')")
    public CategoryResponse createCategory(@Argument String name) {
        return categoryService.invokeCreateCategory(name).getBody();
    }

    @MutationMapping
    public ProductResponse createProduct(@Argument String name,@Argument Float price,@Argument int stock,@Argument int categoryId,@Argument int userId) {
        return productService.invokeCreateProduct(name,price,stock,categoryId,userId).getBody();
    }

    @MutationMapping
    public OrderResponse createOrder(@Argument OrderRequest orderReq) {
        return orderService.invokeCreateOrder(orderReq).getBody();
    }

}
