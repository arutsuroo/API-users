package com.wipro.api.users.update;

import com.wipro.domain.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsersUpdateRestController {

    @Autowired
    private UsersUpdateService service;

    @Autowired
    private UsersUpdateMapper usersMapper;

    @PutMapping("/{id}")
    public ResponseEntity<UsersUpdateResponse> update(@PathVariable("id") Long id, @RequestBody UsersUpdateRequest obj){
        User user = service.update(id, usersMapper.toUsers(obj));
        UsersUpdateResponse response = usersMapper.toUsersDto(user);
        return ResponseEntity.ok().body(response);
    }
}



