package com.example.demo.entities; // package name

import jakarta.persistence.*; // JPA annotations
import lombok.*; // Lombok annotations
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter // Lombok → generates setters
@Getter // Lombok → generates getters
@AllArgsConstructor // Lombok → generates constructor with all fields
@NoArgsConstructor // Lombok → generates no-args constructor
@Builder // Lombok → enables builder pattern
@Entity // Marks this class as a JPA entity
@Table(name = "users") // Maps to "users" table in database
public class User {

    @Id // Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment ID
        private Long id;

    @Column(name = "name") // Column in DB = "name"
    private String name;

    @Column(name = "email") // Column in DB = "email"
    private String email;

    @Column(name = "password") // Column in DB = "password"
    private String password;

    @OneToMany(mappedBy = "user", cascade =  {CascadeType.PERSIST,CascadeType.REMOVE},orphanRemoval = true )
    @Builder.Default // Ensures builder initializes this field
    private List<Address> addresses = new ArrayList<>();

    public void addAddress(Address address) { // helper method to add address
        addresses.add(address); // add address to list
        address.setUser(this); // set back reference
    }

    public void removeAddress(Address address) { // helper method to remove address
        addresses.remove(address); // remove from list
        address.setUser(null); // unlink user
    }

    public void addTags(String tagName) { // helper method to add a tag
        var tag = new Tag(tagName); // create new tag by name
        tags.add(tag); // add to user's tags
        tag.getUsers().add(this); // add this user to tag's users
    }

    public void removeTags(String tagName) { // helper method to remove a tag
        var tag = new Tag(tagName); // create tag instance with name
        tags.remove(tag); // remove from user's tags
        tag.getUsers().remove(null); // ⚠ this should be 'this', not null
    }

    @ManyToMany // Many Users ↔ Many Tags
    @JoinTable( // Defines join table for ManyToMany
            name = "user_tags", // join table name
            joinColumns = @JoinColumn(name = "user_id"), // FK to users
            inverseJoinColumns = @JoinColumn (name = "tag_id") // FK to tags
    )
    @Builder.Default // ensures builder initializes this field
    private Set<Tag> tags = new HashSet<>();

    @OneToOne(mappedBy = "user",cascade = CascadeType.REMOVE)
    private Profile profile;

    @ManyToMany
    @JoinTable
            (
                    name = "wishlist",
                    joinColumns =@JoinColumn (name = "user_id"),
                    inverseJoinColumns =  @JoinColumn(name = "product_id")


            )

    private Set<Product>favoriteProducts=new HashSet<>();

    public void addFavoriteProduct(Product product) {
        favoriteProducts.add(product);

    }
}
