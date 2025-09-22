package com.example.demo.repositories;

import com.example.demo.entities.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

public interface AddressRepository extends CrudRepository<Address, Long> {
}