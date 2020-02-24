package com.wipro.api.users.create;

import com.wipro.api.users.common.UsersDto;
import com.wipro.api.users.common.UsersMapper.UsersMapper;
import com.wipro.domain.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/users")
public class UsersCreateRestController {

    @Autowired
    private UsersCreateSevice service;

    @Autowired
    private UsersMapper usersMapper;

    @PostMapping
    public ResponseEntity<User> insert(@RequestBody UsersDto dto){
        User user = service.insert(usersMapper.fromDto(dto));
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}
