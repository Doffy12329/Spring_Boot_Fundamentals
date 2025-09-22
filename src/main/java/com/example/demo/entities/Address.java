package com.example.demo.entities;

import jakarta.persistence.*;  // JPA annotations (Entity, Id, Column, etc.)
import lombok.*;              // Lombok annotations (Getter, Setter, Builder, etc.)
import java.math.BigInteger;

@Getter                      // Lombok generates getter methods for all fields
@Setter                      // Lombok generates setter methods for all fields
@AllArgsConstructor          // Lombok generates constructor with all arguments
@NoArgsConstructor           // Lombok generates default no-arg constructor
@Builder                     // Lombok enables builder pattern (Address.builder()...)
@ToString                    // Lombok generates toString() method
@Entity                      // Marks this class as a JPA entity (mapped to a table)
@Table(name = "addresses")   // Maps this entity to the "addresses" table in DB
public class Address {

    @Id                                         // Primary key of the table
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                             // Auto-increment column

    @Column(name = "street")                     // "street" column
    private String street;

    @Column(name = "city")                       // "city" column
    private String city;

    @Column(name = "state")                      // "state" column
    private String state;

    @Column(name = "zip")                        // "zip" column
    private String zip;

    @ManyToOne (fetch = FetchType.LAZY)                             // Many addresses → One user
    @JoinColumn(name = "user_id")                // Foreign key in "addresses" table referencing users.id
    @ToString.Exclude                            // Exclude user field from toString() to avoid recursion
    private User user;                           // Reference to the User entity
}
