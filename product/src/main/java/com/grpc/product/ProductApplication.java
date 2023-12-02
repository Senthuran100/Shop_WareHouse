package com.grpc.product;

import com.grpc.product.entity.Category;
import com.grpc.product.entity.Product;
import com.grpc.product.entity.User;
import com.grpc.product.repository.CategoryRepository;
import com.grpc.product.repository.ProductRepository;
import com.grpc.product.repository.UserRepository;
import com.grpc.product.service.CategoryService;
import com.grpc.product.service.UserService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class ProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductApplication.class, args);
	}

	@Bean
	public ApplicationRunner run(CategoryService categoryService, UserService userService, ProductRepository productRepository) throws Exception {
		return (ApplicationArguments args) -> {
//			List<Category> categoryList = Arrays.asList(new Category("cosmetics"), new Category("food"));
//			List<User> userList = Arrays.asList(new User("abc","abc@gmail.com","1234"));
//			userRepository.saveAll(userList);
//			categoryRepository.saveAll(categoryList);

			int intId = 15;

			Category category = categoryService.findById((long)intId);
			User user = userService.findById((long) intId);

			Product product = new Product("biscuit1","P-12345",25,5,category,user);

			productRepository.save(product);
		};

	}

}
