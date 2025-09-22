package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter                   // Lombok: generates getters
@Setter                   // Lombok: generates setters
@NoArgsConstructor        // Lombok: generates a no-args constructor
@ToString                 // Lombok: generates toString() (careful with relationships)
@Entity                   // Marks this as a JPA entity (table in DB)
@Table(name = "tags")     // Maps this entity to "tags" table
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;       // Primary key, auto-incremented

    @Column(name = "name")
    private String name;   // Tag name (ex: "Sports", "Music")

    @ManyToMany(mappedBy = "tags")
    // Many Tags ↔ Many Users
    // "mappedBy = tags" means the User entity owns the relationship
    @ToString.Exclude
    // Prevents infinite recursion when calling toString()
    private Set<User> users = new HashSet<>();
    // A Tag can belong to many Users

    // Custom constructor for convenience
    public Tag(String name) {
        this.name = name;
    }
}
