package com.wipro.api.roles.detail;

import com.wipro.common.exceptions.ResourceNotFoundException;
import com.wipro.domain.role.Role;
import com.wipro.domain.role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleDetailService {

    @Autowired
    private RoleRepository repository;

    public Role findById(Long id){
        Optional<Role> obj = repository.findById(id);
        return obj.orElseThrow(()-> new ResourceNotFoundException(id));
    }
}
