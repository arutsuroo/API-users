package com.wipro.api.roles.update;

import com.wipro.common.exceptions.ResourceNotFoundException;
import com.wipro.domain.role.Role;
import com.wipro.domain.role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class RoleUpdateService {

    @Autowired
    private RoleRepository repository;

    public Role update(Long id, Role obj){
        Role entity = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException(id));
        updateData(entity, obj);
        return repository.save(entity);
    }

    public void updateData(Role entity, Role obj){
            entity.setName(obj.getName());
    }
}
