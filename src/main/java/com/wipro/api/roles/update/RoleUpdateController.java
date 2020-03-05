package com.wipro.api.roles.update;

import com.wipro.domain.role.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roles")
public class RoleUpdateController {

    @Autowired
    private RoleUpdateService service;

    @Autowired
    private RoleUpdateMapper mapper;

    @PutMapping("/{id}")
    public ResponseEntity<RoleUpdateResponse> update(@PathVariable("id") Long id, @RequestBody RoleUpdateRequest obj){
        Role role = service.update(id, mapper.toRoles(obj));
        RoleUpdateResponse response = mapper.toRolesDto(role);
        return ResponseEntity.ok().body(response);
    }

}
