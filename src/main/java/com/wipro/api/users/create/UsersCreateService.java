package com.wipro.api.users.create;

import com.wipro.api.roles.detail.RoleDetailService;
import com.wipro.domain.role.Role;
import com.wipro.domain.users.User;
import com.wipro.domain.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersCreateService {

    @Autowired
    private UserRepository repository;

    @Autowired
    RoleDetailService service;

    public User insert(User obj, long idRole){
        Role role = service.findById(idRole);
        obj.setRoles(role);
        return repository.save(obj);
    }

}
