package com.wipro.api.roles.delete;

import com.wipro.api.roles.create.RoleCreateService;
import com.wipro.domain.role.Role;
import com.wipro.domain.role.RoleRepository;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.Optional;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

class RoleDeleteServiceTest {

    @Ignore
    public void delete_existingId_success(){
        new TestSpec()
                .given_RoleDeleteService_with_existingId()
                .given_roleRepository_findById_return_validRole()
                .when_delete()
                .then_delete_return_success();
    }


    class TestSpec {

        @Mock
        RoleRepository repository;

        @InjectMocks
        RoleCreateService roleCreateService;

        @InjectMocks
        RoleDeleteService roleDeleteService;

        Role role;
        Role roleInserted;

        TestSpec(){
            MockitoAnnotations.initMocks(this);
        }

        public TestSpec given_RoleDeleteService_with_existingId(){
            role = new Role();
            role.setId(1L);
            role.setName("Admin");
            return this;
        }

        public TestSpec given_RoleDeleteService_with_nonexistingId(){
            role = new Role();
            role.setName("Admin");
            return this;
        }

        public TestSpec given_roleRepository_findById_return_validRole(){
            BDDMockito.given(repository.save(any(Role.class))).willReturn(role);
            return this;
        }

        public TestSpec given_roleRepository_findById_return_null(){
            BDDMockito.given(repository.findById(1L)).willReturn(Optional.empty());
            return this;
        }

        public TestSpec when_call_insert() throws IllegalArgumentException{
            roleInserted = roleCreateService.insert(role);
            return this;
        }


        public TestSpec then_delete_return_success(){
            //then(roleDeleteService.delete(role.getId())).should()
            return this;
        }

        public TestSpec then_exception_thrown_with_message(){
            verify(repository).deleteById(null);
            return this;
        }

        public TestSpec when_delete(){
            roleDeleteService.delete(roleInserted.getId());
            return this;
        }

    }

}