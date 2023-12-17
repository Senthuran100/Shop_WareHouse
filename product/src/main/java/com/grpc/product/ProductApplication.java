package com.grpc.product;

import com.grpc.product.entity.*;
import com.grpc.product.repository.*;
import com.grpc.product.service.impl.CategoryServiceImpl;
import com.grpc.product.service.impl.UserServiceImpl;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@SpringBootApplication
public class ProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductApplication.class, args);
	}

	@Bean
	public ApplicationRunner run(CategoryServiceImpl categoryService, UserServiceImpl userService, ProductRepository productRepository,
								 UserRepository userRepository, CategoryRepository categoryRepository, OrderRepository orderRepository, OrderProductRepository orderProductRepository) throws Exception {
		return (ApplicationArguments args) -> {
//			List<Category> categoryList = Arrays.asList(new Category("cosmetics"), new Category("food"));
//			List<User> userList = Arrays.asList(new User("abc","abc@gmail.com","1234"));
//			userRepository.saveAll(userList);
//			categoryRepository.saveAll(categoryList);

			int intId = 1;

			Category category = categoryService.findById((long)intId);
			User user = userService.findById((long) intId);

//			Product product = new Product("biscuit1","P-12345",25,5,category,user);
//			productRepository.save(product);

			String uuidAsString = UUID.randomUUID().toString();

			Optional<Product> product = productRepository.findById((long) intId);

			Order order = new Order(uuidAsString, LocalDateTime.now(),user);


//			orderRepository.save(order);

			Optional<Order> order1 = orderRepository.findById((long) intId);
			OrderProduct orderProduct = new OrderProduct(5,order1.get(),product.get());
			orderProductRepository.save(orderProduct);

		};

	}

}
