package com.wipro.api.roles.list;

import com.wipro.domain.role.Role;
import com.wipro.domain.role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleListService {

    @Autowired
    private RoleRepository repository;

    public List<Role> findAll(){
        return repository.findAll();
    }
}
