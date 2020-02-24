package com.wipro.api.users.create;

import com.wipro.domain.users.User;
import com.wipro.domain.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersCreateSevice {

    @Autowired
    private UserRepository repository;

    public User insert(User obj){
        return repository.save(obj);
    }

}
