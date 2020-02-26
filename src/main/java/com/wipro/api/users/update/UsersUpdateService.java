package com.wipro.api.users.update;

import com.wipro.common.exceptions.ResourceNotFoundException;
import com.wipro.domain.users.User;
import com.wipro.domain.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class UsersUpdateService {

    @Autowired
    private UserRepository repository;

    public User update(Long id, User obj) {
        User entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        updateData(entity, obj);
        return repository.save(entity);

    }

    public void updateData(User entity, User obj){
        entity.setEmail(obj.getEmail());
    }



}
