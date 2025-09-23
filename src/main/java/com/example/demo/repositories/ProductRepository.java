package com.example.demo.repositories;

import com.example.demo.entities.Category;
import com.example.demo.entities.Product;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {
    //Derived   Query Methods
    //String Conventions
    List<Product> findByName(String name);
    List<Product> findByNameLike(String name);
    List<Product> findByNameNotLike(String name);
    List<Product> findByNameContaining(String name);
    List<Product> findByNameStartingWith(String name);
    List<Product> findByNameEndingWith(String name);
    List<Product> findByNameEndingWithIgnoreCase(String name);

    //Numbers
    List<Product> findByPrice(BigDecimal price);
    List<Product> findByPriceGreaterThan(BigDecimal price);
    List<Product> findByPriceGreaterThanEqual(BigDecimal price);
    List<Product> findByPriceLessThan(BigDecimal price);
    List<Product> findByPriceLessThanEqual(BigDecimal price);
    List<Product> findByPriceBetween(BigDecimal min, BigDecimal max);

    //Null
    List<Product> findByDescriptionNull();
    List<Product> findByDescriptionNotNull();

    //Multiple Conditions
    List<Product> findByDescriptionNullAndNameNull();

    //Sort OrderBy
    List<Product> findByNameOrderByPrice(String name);

    //Limit (Top/First)
    List<Product>findTop5ByNameOrderByPrice(String name);
    List<Product>findTop5ByNameLikeOrderByPrice(String name);

    //Find Product whose prices are in a given range and sort by name
    //Using SQL
// Finds all products that belong to a category and have a price within the given range (min to max)
    @Query("select p from Product p join p.category where p.price between :min and :max")
    List<Product> findProducts(
            @Param("min") BigDecimal min,   // @Param("min") → fills the :min in the query
            @Param("max") BigDecimal max);  // @Param("max") → fills the :max in the query

    // Counts how many products are within the given price range (min to max)
    @Query("select count(*) from Product p where p.price between :min and :max")
    long countProducts(
            @Param("min") BigDecimal min,   // @Param("min") → fills the :min in the query
            @Param("max") BigDecimal max);  // @Param("max") → fills the :max in the query

    // Updates the price of all products that belong to a specific category
    @Modifying
    @Query("update Product p set p.price = :newPrice where p.category.id = :categoryId")
    void updatePriceByCategory(
            @Param("newPrice") BigDecimal newPrice, // @Param("newPrice") → fills the :newPrice in the query
            @Param("categoryId") Byte categoryId);  // @Param("categoryId") → fills the :categoryId in the query

    List<Product> findByCategory(Category category);
}