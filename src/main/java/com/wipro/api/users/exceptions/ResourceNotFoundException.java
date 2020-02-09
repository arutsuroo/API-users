package com.wipro.api.users.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(Object id){
        super("User not found. Id " + id);
    }

}
