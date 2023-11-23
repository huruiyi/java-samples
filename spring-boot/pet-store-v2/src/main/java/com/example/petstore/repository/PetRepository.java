package com.example.petstore.repository;

import com.example.petstore.model.Pet;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface PetRepository extends ReactiveCrudRepository<Pet, String> { }
