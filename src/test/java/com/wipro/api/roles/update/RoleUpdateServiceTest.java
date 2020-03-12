package com.wipro.api.roles.update;

import com.wipro.domain.role.Role;
import com.wipro.domain.role.RoleRepository;
import org.junit.Ignore;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.BDDMockito.given;

public class RoleUpdateServiceTest {

    @Ignore
    public void getOne_existingId_success(){
        new TestSpec()
                .given_RoleUpdateRequest_with_existingId()
                .given_roleRepository_findById_return_validRole()
                .when_save()
                .then_validRoleUpdateResponse_isReturned();
    }


    class TestSpec {

        @InjectMocks
        RoleUpdateService service;

        @Mock
        RoleRepository repository;

        Role role;
        Role roleName;
        Role roleUpdated;

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
            roleUpdated = service.update(1L, roleName);
            return this;
        }

        public TestSpec then_validRoleUpdateResponse_isReturned(){
            assertThat(roleUpdated.getName()).isEqualTo(roleName.getName());
            return this;
        }


    }

}