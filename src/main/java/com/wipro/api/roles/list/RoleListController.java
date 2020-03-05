package com.wipro.api.roles.list;

import com.wipro.domain.role.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleListController {

    @Autowired
    private RoleListService service;

    @Autowired
    private RoleListMapper mapper;

    @GetMapping
    public ResponseEntity<List<RoleListResponse>> findAll(){
        List<Role> list;
        List<RoleListResponse> response = new ArrayList<>();
        list = service.findAll();
        for (Role role : list){
            response.add(mapper.toRolesDto(role));
        }
        return ResponseEntity.ok().body(response);
    }

}
