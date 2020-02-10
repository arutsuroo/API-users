package com.wipro.api.users.update;

import com.wipro.common.exceptions.ResourceNotFoundException;
import com.wipro.domain.users.User;
import com.wipro.domain.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class UserUpdateService {

    @Autowired
    private UserRepository repository;

    public User update(Long id, User obj) {
        try {
            User entity = repository.getOne(id);
            updateData(entity, obj);
            return repository.save(entity);
        }
        catch (EntityNotFoundException e){
            throw new ResourceNotFoundException(id);
        }
    }

    public void updateData(User entity, User obj){
        entity.setEmail(obj.getEmail());
    }



}
