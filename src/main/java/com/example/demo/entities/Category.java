package com.example.demo.entities;

// Importing JPA annotations and Lombok
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;
@NoArgsConstructor
@Getter  // Lombok generates getters for all fields
@Setter  // Lombok generates setters for all fields
@Entity  // Marks this class as a JPA entity (maps to a DB table)
@Table(name = "categories")  // The entity maps to the "categories" table
public class Category {

    @Id  // Marks this field as the Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")  // Maps this field to the "id" column
    private Byte id;  // Using Byte type for ID (tinyint in SQL)

    @Column(name = "name")  // Maps this field to the "name" column
    private String name;

    @OneToMany(mappedBy = "category")
    // One Category → Many Products
    // "mappedBy = category" means the "Product" entity owns the relationship (it has @ManyToOne pointing back to Category)
    private Set<Product> products = new LinkedHashSet<>();

    public Category(String name) {
        this.name = name;
    }


    // Stores all products in this category (avoids duplicates with Set)
    // LinkedHashSet keeps insertion order
}
