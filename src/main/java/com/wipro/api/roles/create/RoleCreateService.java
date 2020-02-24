package com.wipro.api.roles.create;

import com.wipro.domain.role.Role;
import com.wipro.domain.role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleCreateService {

    @Autowired
    private RoleRepository repository;

    public Role insert(Role obj){
        return repository.save(obj);
    }
}
