package com.example.demo.repositories;

import com.example.demo.entities.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}