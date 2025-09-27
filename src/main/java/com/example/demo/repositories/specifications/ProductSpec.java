package com.example.demo.repositories.specifications;

import com.example.demo.entities.Product;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class ProductSpec {
    public static Specification<Product> hasName(String name) {
        return (root, query, cb) -> cb.like(root.get("name"), "%"+name+"%");
    }
    public static Specification<Product> hasPriceLessGreaterThanEqualTo(BigDecimal price) {
        return (root, query, cb) ->  cb.greaterThanOrEqualTo(root.get("price"), price);
    }
    public static Specification<Product> hasPriceLessThanOrEqualTo(BigDecimal price) {
        return (root, query, cb) ->  cb.lessThanOrEqualTo(root.get("price"), price);
    }
}