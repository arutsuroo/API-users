package com.wipro.users.controller;

import com.wipro.users.entity.User;
import com.wipro.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
class UserController {

    @Autowired
    private UserRepository repository;

    @GetMapping("/users")
    List<User> all() {
        return repository.findAll();
    }

    @PostMapping("/users")
    User newUser(@RequestBody User newUser) {
        return repository.save(newUser);
    }

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

    @DeleteMapping("/users/{id}")
    void deleteUser(@PathVariable Long id) {
        repository.deleteById(id);
    }
}

