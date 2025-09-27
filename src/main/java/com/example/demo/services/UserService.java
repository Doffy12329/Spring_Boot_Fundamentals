package com.example.demo.services;


import com.example.demo.entities.Product;
import com.example.demo.entities.User;
import com.example.demo.repositories.*;
import com.example.demo.repositories.specifications.ProductSpec;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
@AllArgsConstructor
public class    UserService {
    private final UserRepository userRepository;
    private final EntityManager entityManager;
    private final ProfileRepository profileRepository;
    private final AddressRepository addressRepository;
    private final ProductRepository productRepository;

    @Transactional
    public void showEntityStates(){
        var user = User.builder()
                .name("Jonathan")
                .email("jonathan")
                .password("password")
                .build();

        if(entityManager.contains(user))
            System.out.println("Persistent");


        else
            System.out.println("Not Persistent");

        userRepository.save(user);

        if(entityManager.contains(user)){
            System.out.println("Persistent");
        }

        else {
            System.out.println("Not Persistent");
        }


    }
    @Transactional
    public void showRelatedEntities(){
        var profile = profileRepository.findById(5L).orElseThrow();
        System.out.println(profile.getUser().getEmail());
    }
    public void fetchAddresses(){
    var address = addressRepository.findById(1L).orElseThrow();


    }
//    public void persistRealated(){
//        var user = User.builder()
//                .name("Jonathan")
//                .email("jonathan@gmail")
//                .password("password")
//                .build();
//
//        var address = Address.builder()
//                .state("state")
//                .city("city")
//                .zip("zip")
//                .street("street")
//                .build();
//
//        user.addAddress(address);
//        userRepository.save(user);
//    }
//    public void manageProducts(){
//        var category = new Category("Category 1");
//        var product = Product.builder()
//                .name("Product 1")
//                .description("description")
//                .price(BigDecimal.valueOf(10.99))
//                .category(category)
//                .build();
//
//        productRepository.save(product);
//
//    }
    @Transactional
    public void deleteRelated(){
      var user = userRepository.findById(5L).orElseThrow();
      var address = user.getAddresses().getFirst();
      user.removeAddress(address);
      userRepository.save(user);
    }
    @Transactional
        public void manageProducts(){
//           var user = userRepository.findById(5L).orElseThrow();
//           var products = productRepository.findAll();
//           products.forEach(user::addFavoriteProduct);
//           userRepository.save(user);
           productRepository.deleteById(4L);

    }
    @Transactional
    public void updateProductPrices(){
        productRepository.updatePriceByCategory(BigDecimal.valueOf(10),(byte)1);
    }
    @Transactional
    public void fetchProducts(){
       var product = new Product();
       product.setName("product");

        var matcher = ExampleMatcher.matching()

                .withIncludeNullValues()
                .withIgnorePaths("id","description")
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        var example = Example.of(product, matcher);

       var products = productRepository.findAll(example);
       products.forEach(System.out::println);



    }


    public void fetchProductsByCriteria(){
        var products = productRepository.findProductsByCriteria("Name", BigDecimal.valueOf(1), null);
        products.forEach(System.out::println);
    }
    
    public void fetchProductsBySpecifications(String name, BigDecimal minPrice, BigDecimal maxPrice){
        Specification<Product> spec = Specification.where(null);

        if(name != null) {
            spec = spec.and(ProductSpec.hasName(name));
        }
        if(minPrice != null) {
            spec = spec.and(ProductSpec.hasPriceLessGreaterThanEqualTo(minPrice));
        }
        if(maxPrice != null) {
            spec = spec.and(ProductSpec.hasPriceLessThanOrEqualTo(maxPrice));
        }
        productRepository.findAll(spec).forEach(System.out::println);

    }
    
    @Transactional
    public void fetchUsers(){
       var users = userRepository.findAllWithAddresses();
       users.forEach( u-> {
           System.out.println(u);
           u.getAddresses().forEach(System.out::println);
       });
    }

    public void fetchSortedProducts(){
       var sort = Sort.by("name").and(
                Sort.by("price").descending()
        );
       productRepository.findAll(sort).forEach(System.out::println);

    }

    public void fetchPaginatedProducts(int pageNumber, int size){
        PageRequest pageRequest = PageRequest.of(pageNumber,size);
        Page<Product> page = productRepository.findAll(pageRequest);

        var products = page.getContent();
        products .forEach(System.out::println);

        var totalPages = page.getTotalPages();
        var totalElements = page.getTotalElements();

        System.out.println("Total elements: " + totalElements);
        System.out.println("Total pages: " + totalPages);
    }


    @Transactional
    public void loyaltyProfiles(){
        var user = userRepository.findLoyalUsers(1);
        user.forEach( p-> System.out.println(p.getId()+ p.getId()+" :" +p.getEmail()));


    }
    
}
