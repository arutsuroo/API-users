package com.wipro.api.roles.delete;

import com.wipro.common.exceptions.DatabaseException;
import com.wipro.common.exceptions.ResourceNotFoundException;
import com.wipro.domain.role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class RoleDeleteService {

    @Autowired
    private RoleRepository repository;

    public void delete(Long id){
        try{
            repository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException(id);
        }
        catch (DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        }
    }
}
