package com.wipro.api.users.detail;

import com.wipro.api.users.common.UsersDto;
import com.wipro.api.users.common.UsersMapper.UsersMapper;
import com.wipro.domain.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/users")
public class UsersDetailRestController {

    @Autowired
    private UsersDetailService service;

    @Autowired
    private UsersMapper usersMapper;

    @GetMapping("/{id}")
    public ResponseEntity<UsersDto> findById(@PathVariable Long id) {
        User obj = service.findById(id);
        UsersDto response = usersMapper.toUserDto(obj);
        return ResponseEntity.ok().body(response);
    }

}
