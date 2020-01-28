package com.wipro.domain.users;

class UserNotFoundException extends RuntimeException {

    UserNotFoundException(Long id){
        super("Could not find user " + id);
    }
}
