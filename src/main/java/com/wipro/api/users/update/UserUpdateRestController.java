package com.wipro.api.users.update;

import com.wipro.domain.users.User;
import com.wipro.domain.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UserUpdateRestController {

    @Autowired
    private UserRepository repository;

    @PutMapping("/users/{id}")
    User replaceUser(@RequestBody User newUser, @PathVariable Long id) {
        Optional<User> savedUser = repository.findById(id);
        if(savedUser.isPresent()){
            User user = savedUser.get();
            user.setUserName(newUser.getUserName());
            user.setFirstName(newUser.getFirstName());
            user.setLastName(newUser.getLastName());
            user.setBirthDate(newUser.getBirthDate());
            user.setEmail(newUser.getEmail());
            return repository.save(user);
        }
        else {
            newUser.setId(id);
            return repository.save(newUser);
        }
    }


}
