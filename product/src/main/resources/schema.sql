CREATE TABLE IF NOT EXISTS `roles` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
   PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `user_roles` (
  `user_id` bigint NOT NULL,
  `role_id` int NOT NULL,
   PRIMARY KEY (`user_id`,`role_id`),
   FOREIGN KEY (role_id) REFERENCES roles(id),
   FOREIGN KEY (user_id) REFERENCES users(id)
);



CREATE TABLE IF NOT EXISTS `category`(
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
   PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `products`(
    `id` bigint NOT NULL AUTO_INCREMENT,
    `product_code` varchar(255) NOT NULL,
    `name` varchar(255) NOT NULL,
    `price` decimal(12, 2) NOT NULL,
    `quantity` int NOT NULL,
    `created_date` datetime DEFAULT NULL,
    `created_by` bigint NOT NULL,
    `category_id` bigint NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY(created_by) REFERENCES users(id),
    FOREIGN KEY(category_id) REFERENCES category(id),
    UNIQUE (`product_code`)
);

CREATE TABLE IF NOT EXISTS `orders` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `order_id` varchar(255) NOT NULL,
  `quantity` int NOT NULL,
  `order_date` datetime DEFAULT NULL,
  `user_id` bigint NOT NULL,
  `product_id` bigint NOT NULL,
   PRIMARY KEY (`id`),
   FOREIGN KEY(user_id) REFERENCES users(id),
   FOREIGN KEY(product_id) REFERENCES products(id),
   UNIQUE (`order_id`)
);

CREATE TABLE IF NOT EXISTS `order_product` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `product_id` bigint NOT NULL,
    `order_id` bigint NOT NULL,
    `quantity` int NOT NULL,
     PRIMARY KEY (`id`),
     FOREIGN KEY(product_id) REFERENCES products(id),
     FOREIGN KEY(order_id) REFERENCES orders(id)
);