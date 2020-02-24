package com.wipro.api.roles.detail;

import com.wipro.domain.role.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roles")
public class RoleDetailController {

    @Autowired
    private RoleDetailService service;

    @GetMapping("/{id}")
    public ResponseEntity<Role> findById(@PathVariable Long id){
        Role obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }
}
