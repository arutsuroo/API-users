package com.wipro.api.users.delete;

import com.wipro.common.exceptions.DatabaseException;
import com.wipro.common.exceptions.ResourceNotFoundException;
import com.wipro.domain.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class UsersDeleteService {

    @Autowired
    private UserRepository repository;

    public void delete(Long id) {
        try {
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
