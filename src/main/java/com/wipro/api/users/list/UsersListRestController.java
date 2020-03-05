package com.wipro.api.users.list;

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
    private UsersListMapper usersMapper;

    @GetMapping
    public ResponseEntity<List<UsersListResponse>> findAll() {
        List<User> list;
        List<UsersListResponse> response = new ArrayList<>();
        list = service.findAll();
        for (User user : list){
            response.add(usersMapper.toUsersDto(user));
        }
        return ResponseEntity.ok().body(response);
    }
}
