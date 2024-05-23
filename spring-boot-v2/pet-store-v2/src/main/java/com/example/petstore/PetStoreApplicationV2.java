package com.example.petstore;

import com.example.petstore.model.Pet;
import com.example.petstore.repository.PetRepository;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PetStoreApplicationV2 implements CommandLineRunner {

  public static void main(String[] args) {
    SpringApplication.run(PetStoreApplicationV2.class, args);
  }

  @Autowired
  private PetRepository petRepository;

  @Override
  public void run(String... args) {
    Pet pet1 = Pet.builder().id(UUID.randomUUID().toString()).cost(10L).numberInStock(100).itemName("小花猫").build();
    petRepository.save(pet1);
    System.out.println(pet1.getId());
    Pet pet2 = Pet.builder().id(UUID.randomUUID().toString()).cost(20L).numberInStock(100).itemName("小狗子").build();
    petRepository.save(pet2);
    System.out.println(pet2.getId());
  }
}
