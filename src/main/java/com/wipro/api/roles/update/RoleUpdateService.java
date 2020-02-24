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
        try {
            Role entity = repository.getOne(id);
            updateData(entity, obj);
            return repository.save(entity);
        } catch (EntityNotFoundException e){
            throw new ResourceNotFoundException(id);
        }
    }

    public void updateData(Role entity, Role obj){
        entity.setName(obj.getName());
    }
}
