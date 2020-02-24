package com.wipro.api.roles.create;

import com.wipro.domain.role.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roles")
public class RoleCreateController {

    @Autowired
    private RoleCreateService service;

    @PostMapping
    public ResponseEntity<Role> insert(@RequestBody Role obj){
        Role role = service.insert(obj);
        return new ResponseEntity<>(role, HttpStatus.CREATED);
    }

}
