package com.wipro.api.users.create;

import com.wipro.domain.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.Validator;

@RestController
@RequestMapping(value = "/users")
public class UsersCreateRestController {

    @Autowired
    private UsersCreateSevice service;

    @Autowired
    private UsersCreateMapper usersMapper;

    @PostMapping
    public ResponseEntity<UsersCreateResponse> insert(@RequestBody @Valid UsersCreateRequest obj){
        User user = service.insert(usersMapper.toUsers(obj), 1L);
        UsersCreateResponse response = usersMapper.toUsersDto(user);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
