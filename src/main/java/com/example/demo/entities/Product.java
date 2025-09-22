package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter   // Lombok generates getters for all fields
@Setter   // Lombok generates setters for all fields
@Entity   // Marks this class as a JPA entity (maps to DB table)
@Table(name = "products")  // Maps this entity to the "products" table
public class Product {

    @Id  // Marks this field as the Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Auto-increment (DB generates ID values)
    @Column(name = "id")
    private Long id;  // Primary Key (bigint in DB)

    @Column(name="description")
    private String description;  // Product description

    @Column(name = "name")
    private String name;  // Product name

    @Column(name = "price")
    private BigDecimal price;  // Price with decimals (good for money)

    @ManyToOne(cascade = CascadeType.PERSIST)
    // Many Products → One Category
    @JoinColumn(name = "category_id")
    // Creates a foreign key "category_id" in the products table → points to categories.id
    private Category category;  // The category this product belongs to
}
