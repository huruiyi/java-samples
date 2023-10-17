package com.example.petstore.model;

import lombok.Data;

@Data
public class PetStoreUserDTO {
    private String email;
    private String password;
    private String name;
}
