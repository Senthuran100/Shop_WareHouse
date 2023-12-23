package com.grpc.product.service.impl;

import com.grpc.product.entity.Order;
import com.grpc.product.entity.OrderProduct;
import com.grpc.product.entity.Product;
import com.grpc.product.entity.User;
import com.grpc.product.exception.RequestNotValidException;
import com.grpc.product.payload.request.OrderProductRequest;
import com.grpc.product.payload.request.OrderRequest;
import com.grpc.product.payload.response.OrderProductResponse;
import com.grpc.product.payload.response.OrderResponse;
import com.grpc.product.repository.OrderProductRepository;
import com.grpc.product.repository.OrderRepository;
import com.grpc.product.repository.ProductRepository;
import com.grpc.product.service.OrderService;
import com.grpc.product.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private UserService userService;
    private ProductRepository productRepository;
    private OrderRepository orderRepository;
    private OrderProductRepository orderProductRepository;

    public OrderResponse saveOrder(OrderRequest orderRequest) {
        User user = userService.findById(orderRequest.getUserId());

        List<Long> productIds = orderRequest.getOrderProductList().stream()
                .map(OrderProductRequest::getProductId).toList();
        List<Product> productList = productRepository.findByProductIdIn(productIds);

        // Validate whether the stock is greater than the quantity of the order request.
        productList.forEach(x-> {
           OrderProductRequest orderProductRequest = orderRequest.getOrderProductList().stream()
                    .filter(y->y.getProductId()==x.getId()).findFirst().orElseThrow(() -> new EntityNotFoundException("Product not found with id " + x.getId()));

            if(x.getStock() > orderProductRequest.getQuantity()) {
                x.setStock(x.getStock()-orderProductRequest.getQuantity());
            } else {
                throw new RequestNotValidException("Order Request Quantity exceeded the product Stock level");
            }
        });

        Map<Long, Product> productMap = productList.stream()
                .collect(Collectors.toMap(Product::getId,product -> product));

        String orderId = UUID.randomUUID().toString();

        Order order = new Order(orderId, LocalDateTime.now(),user);

        Order savedOrder = orderRepository.save(order);

        List<OrderProduct> orderProductList = orderRequest.getOrderProductList().stream()
                .map(x->new OrderProduct(x.getQuantity(),savedOrder,productMap.get(x.getProductId())))
                .toList();

        List<OrderProduct> savedOrderProducts = orderProductRepository.saveAll(orderProductList);

        productRepository.saveAll(productList);

        return OrderResponse.builder()
                .orderId(savedOrder.getOrderId())
                .id(savedOrder.getId())
                .created_date(savedOrder.getCreated_date())
                .userId(user.getId())
                .orderProductList(savedOrderProducts.stream().map(x-> OrderProductResponse.builder()
                        .quantity(x.getQuantity())
                        .productId(x.getProduct().getId())
                        .id(x.getId())
                        .build()
                ).toList())
                .build();
    };
}
