package com.wipro.api.users.update;

import com.wipro.domain.users.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users")
public class UserUpdateRestController {

    @Autowired
    private UserUpdateService service;

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User obj){
        obj = service.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }
}



