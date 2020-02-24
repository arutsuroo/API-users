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

    @PutMapping("/{id}")
    public ResponseEntity<Role> update(@PathVariable("id") Long id, @RequestBody Role obj){
        obj = service.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }

}
