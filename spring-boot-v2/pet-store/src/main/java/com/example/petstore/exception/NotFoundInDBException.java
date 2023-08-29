package com.example.petstore.exception;

public class NotFoundInDBException extends PetStoreException {

    public NotFoundInDBException(String message) {
        super(message);
    }
}
