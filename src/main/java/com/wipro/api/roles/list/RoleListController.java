package com.wipro.api.roles.list;

import com.wipro.domain.role.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleListController {

    @Autowired
    private RoleListService service;

    @GetMapping
    public ResponseEntity<List<Role>> findAll(){
        List<Role> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

}
