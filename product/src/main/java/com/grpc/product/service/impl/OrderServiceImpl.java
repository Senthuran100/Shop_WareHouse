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
import com.grpc.product.service.ElasticSearchService;
import com.grpc.product.service.OrderService;
import com.grpc.product.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private UserService userService;
    private ProductRepository productRepository;
    private OrderRepository orderRepository;
    private OrderProductRepository orderProductRepository;


    public OrderResponse saveOrder(OrderRequest orderRequest) {
        log.info("Order Creation with userId: "+orderRequest.getUserId());
        User user = userService.findById(orderRequest.getUserId());

        Map<Long, Integer> orderProductRequestMap = orderRequest.getOrderProducts().stream().collect(Collectors.groupingBy(OrderProductRequest::getProductId,
                Collectors.summingInt(OrderProductRequest::getQuantity)));
        List<OrderProductRequest> orderProductRequests = orderProductRequestMap.entrySet().stream().map(e->new OrderProductRequest(e.getKey(),e.getValue())).toList();
        orderRequest.setOrderProducts(orderProductRequests);

        List<Long> productIds = orderRequest.getOrderProducts().stream()
                .map(OrderProductRequest::getProductId).toList();
        List<Product> productList = productRepository.findByProductIdIn(productIds);

        // validate whether the stock is greater than the quantity of the order request.
        validateStockGreaterThanProduct(productList, orderRequest);

        Map<Long, Product> productMap = productList.stream()
                .collect(Collectors.toMap(Product::getId,product -> product));

        String orderId = UUID.randomUUID().toString();

        Order order = new Order(orderId, LocalDateTime.now(),user);

        Order savedOrder = orderRepository.save(order);

        List<OrderProduct> orderProductList = orderRequest.getOrderProducts().stream()
                .map(x->new OrderProduct(x.getQuantity(),savedOrder,productMap.get(x.getProductId())))
                .toList();

        List<OrderProduct> savedOrderProducts = orderProductRepository.saveAll(orderProductList);
        // Finally Saving the product List after deducting the quantity from stock.
        productRepository.saveAll(productList);

//        elasticSearchService.saveOrder(savedOrder);

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

    /**
     * Validate whether the stock is greater than the quantity of the order request.
     */
    private void validateStockGreaterThanProduct(List<Product> productList, OrderRequest orderRequest) {
        productList.forEach(x-> {
            OrderProductRequest orderProductRequest = orderRequest.getOrderProducts().stream()
                    .filter(y->y.getProductId()==x.getId()).findFirst().orElseThrow(() -> new EntityNotFoundException("Product not found with id " + x.getId()));

            if(x.getStock() >= orderProductRequest.getQuantity()) {
                x.setStock(x.getStock()-orderProductRequest.getQuantity());
            } else {
                throw new RequestNotValidException("Order Request Quantity exceeded the product Stock level");
            }
        });
    }
}
