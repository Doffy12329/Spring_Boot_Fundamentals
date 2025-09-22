package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.*;

@Builder                 // Lombok: generates builder pattern
@NoArgsConstructor       // Lombok: no-args constructor
@AllArgsConstructor      // Lombok: all-args constructor
@Getter                  // Lombok: generates getters
@Setter                  // Lombok: generates setters
@Entity                  // Marks this as a JPA entity (table in DB)
@Table(name = "profiles") // Maps entity to "profiles" table
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;   // Primary key (auto-increment in DB)

    @Column(name = "bio")
    private String bio;  // User biography

    @Column(name = "phone_number")
    private String phone_number;  // Phone number

    @Column(name = "date_of_birth")
    private String date_of_birth;  // Date of birth (better as LocalDate in real apps)

    @Column(name = "loyalty_points")
    private Integer loyalty_points;  // Loyalty points (integer)

    @OneToOne(fetch = FetchType.LAZY)             // One User ↔ One Profile
    @JoinColumn(name = "id")
    // "profiles.id" will also act as FK to "users.id"
    @MapsId
    // Shares the same primary key as the User entity
    @ToString.Exclude      // Prevent infinite loop in toString()
    private User user;     // Linked User
}
