package com.grpc.product.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "product_code",unique = true)
    private String productCode;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private double price;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "created_date")
    private LocalDateTime created_date;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "created_by")
    private User user;

    public Product(String name, String productCode, double price, int quantity,Category category, User user ) {
        this.name = name;
        this.productCode = productCode;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.user = user;
        this.created_date = LocalDateTime.now();
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.ALL)
    private List<Order> orderList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product= (Product) o;
        return Objects.equals(id, product.id) && Objects.equals(name, product.name) && Objects.equals(productCode,
                product.productCode) && Objects.equals(category, product.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productCode, name, category);
    }

}