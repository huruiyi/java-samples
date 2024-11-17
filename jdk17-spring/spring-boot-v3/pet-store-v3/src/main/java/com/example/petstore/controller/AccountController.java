package com.example.petstore.controller;

import com.example.petstore.model.PetStoreUserDTO;
import com.example.petstore.service.IReactivePetStoreUserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class AccountController {

  private final IReactivePetStoreUserService petStoreUserService;

  public AccountController(IReactivePetStoreUserService petStoreUserService) {
    this.petStoreUserService = petStoreUserService;
  }

  @PostMapping("/register")
  public Mono<Void> registerUser(PetStoreUserDTO dto) {
    return this.petStoreUserService.registerNewUser(dto);
  }

}
