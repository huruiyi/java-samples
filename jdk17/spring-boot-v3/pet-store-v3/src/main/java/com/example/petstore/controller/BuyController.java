package com.example.petstore.controller;


import com.example.petstore.exception.NotFoundInDBException;
import com.example.petstore.exception.UserDoNotHaveEnoughFoundsException;
import com.example.petstore.model.Pet;
import com.example.petstore.model.PetStoreUser;
import com.example.petstore.repository.PetRepository;
import com.example.petstore.repository.PetStoreUserRepository;
import java.security.Principal;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class BuyController {

  private PetRepository petRepository;
  private PetStoreUserRepository petStoreUserRepository;

  public BuyController(PetRepository petRepository, PetStoreUserRepository petStoreUserRepository) {
    this.petRepository = petRepository;
    this.petStoreUserRepository = petStoreUserRepository;
  }

  @PostMapping("/buy/{petId}")
  public Mono<ResponseEntity<Pet>> buyPet(@PathVariable("petId") String petId, Mono<Principal> principalMono) {

    Mono<PetStoreUser> userMono = principalMono
        .map(principal -> principal.getName())
        .flatMap(email -> petStoreUserRepository.findByEmail(email))
        .switchIfEmpty(Mono.error(new NotFoundInDBException("USer not found in DB")));

    return this.petRepository.findById(petId)
        .switchIfEmpty(Mono.error(new NotFoundInDBException("Pet is not on the records")))
        .zipWith(userMono, (pet, user) -> {

          if (user.getMoneyAvailable() < pet.getCost()) {
            throw new UserDoNotHaveEnoughFoundsException(String.format("The User do not have enough founds!"));
          }

          user.setMoneyAvailable(user.getMoneyAvailable() - pet.getCost());
          pet.setNumberInStock(pet.getNumberInStock() - 1);

          return this.petStoreUserRepository.save(user)
              .then(this.petRepository.save(pet))
              .thenReturn(ResponseEntity.ok(pet));

        }).flatMap(t -> t);
  }
}
