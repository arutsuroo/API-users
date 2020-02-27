package com.wipro.api.users.list;

import com.wipro.api.users.common.UsersDto;
import com.wipro.api.users.common.UsersMapper.UsersMapper;
import com.wipro.domain.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersListRestController {

    @Autowired
    private UsersListService service;

    @Autowired
    private UsersMapper usersMapper;

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        List<User> list = new ArrayList<User>();
        for (User user : list){
            usersMapper.toUserDto(user);
        }
        list = service.findAll();
        return ResponseEntity.ok().body(list);
    }
}
