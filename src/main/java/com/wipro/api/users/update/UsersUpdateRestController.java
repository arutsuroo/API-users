package com.wipro.api.users.update;

import com.wipro.api.users.common.UsersDto;
import com.wipro.api.users.common.UsersMapper.UsersMapper;
import com.wipro.domain.users.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsersUpdateRestController {

    @Autowired
    private UsersUpdateService service;

    @Autowired
    private UsersMapper usersMapper;

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable("id") Long id, @RequestBody UsersDto obj){
        User user = service.update(id, usersMapper.fromDto(obj));
        return ResponseEntity.ok().body(user);
    }
}



