package com.wipro.api.roles.update;

import com.wipro.common.exceptions.ResourceNotFoundException;
import com.wipro.domain.role.Role;
import com.wipro.domain.role.RoleRepository;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.validation.ConstraintViolation;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.BDDMockito.given;

public class RoleUpdateServiceTest {

    @Test
    public void getOne_existingId_success(){
        new TestSpec()
                .given_RoleUpdateRequest_with_existingId()
                .given_roleRepository_findById_return_validRole()
                .given_role_with_new_name()
                .when_save()
                .then_validRoleUpdateResponse_isReturned();
    }


    class TestSpec {

        Role role;
        Role roleName;
        Role roleUpdated;
        Exception exception;
        private Set<ConstraintViolation<Role>> violations;

        @InjectMocks
        RoleUpdateService service;

        @Mock
        RoleRepository repository;


        TestSpec(){
            MockitoAnnotations.initMocks(this);
        }

        public TestSpec given_RoleUpdateRequest_with_existingId(){
            role = new Role();
            role.setId(1L);
            role.setName("Admin");
            return this;
        }

        public TestSpec given_roleRepository_findById_return_validRole(){
            given(repository.findById(roleUpdated.getId())).willReturn(Optional.of(role));
            return this;
        }

        public TestSpec given_role_with_new_name(){
            roleName = new Role();
            roleName.setName("Developer");
            return this;
        }

        public TestSpec when_save(){
            try {
                roleUpdated = service.update(1L, roleName);
            } catch (ResourceNotFoundException e){
                this.exception = e;
            }
            return this;
        }

        public TestSpec then_validRoleUpdateResponse_isReturned(){
            assertThat(roleUpdated).isNotNull();
            assertThat(roleUpdated.getName()).isEqualTo(roleName.getName());
            return this;
        }


    }

}