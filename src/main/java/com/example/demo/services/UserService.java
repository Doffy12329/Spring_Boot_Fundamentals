package com.example.demo.services;


import com.example.demo.entities.User;
import com.example.demo.repositories.*;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final EntityManager entityManager;
    private final ProfileRepository profileRepository;
    private final AddressRepository addressRepository;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
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
//        productRepository.deleteById(4L);

    }
}
