package com.example.demo.repositories;

import com.example.demo.entities.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

public interface ProfileRepository extends CrudRepository<Profile, Long> {
}