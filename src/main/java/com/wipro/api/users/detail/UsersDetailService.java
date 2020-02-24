package com.wipro.api.users.detail;

import com.wipro.api.users.common.UsersDto;
import com.wipro.common.exceptions.ResourceNotFoundException;
import com.wipro.domain.users.User;
import com.wipro.domain.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersDetailService {

    @Autowired
    private UserRepository repository;

    public User findById(Long id) {
        Optional<User> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

}
