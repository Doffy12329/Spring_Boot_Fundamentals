package com.example.demo.repositories;

import com.example.demo.entities.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

public interface CategoryRepository extends CrudRepository<Category, Byte> {
}