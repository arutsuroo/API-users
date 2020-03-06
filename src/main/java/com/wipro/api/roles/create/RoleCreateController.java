package com.wipro.api.roles.create;

import com.wipro.domain.role.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/roles")
public class RoleCreateController {

    @Autowired
    private RoleCreateService service;

    @Autowired
    private RoleCreateMapper mapper;

    @PostMapping
    public ResponseEntity<RoleCreateResponse> insert(@Valid @RequestBody RoleCreateRequest obj){
        Role role = service.insert(mapper.toRoles(obj));
        RoleCreateResponse response = mapper.toRoleDto(role);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
