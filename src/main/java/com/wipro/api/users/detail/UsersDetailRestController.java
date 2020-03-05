package com.wipro.api.users.detail;

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
    private UsersDetailMapper usersMapper;

    @GetMapping("/{id}")
    public ResponseEntity<UsersDetailResponse> findById(@PathVariable Long id) {
        User user = service.findById(id);
        UsersDetailResponse response = usersMapper.toUsersDto(user);
        return ResponseEntity.ok().body(response);
    }

}
