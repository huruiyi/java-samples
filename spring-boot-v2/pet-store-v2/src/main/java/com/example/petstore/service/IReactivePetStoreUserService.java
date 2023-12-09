package com.example.petstore.service;

import com.example.petstore.model.PetStoreUserDTO;
import org.springframework.lang.NonNull;
import reactor.core.publisher.Mono;

public interface IReactivePetStoreUserService {

    Mono<Void> registerNewUser(@NonNull PetStoreUserDTO petStoreUser);
}
