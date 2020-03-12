package com.wipro.api.roles.create;

import com.wipro.domain.role.Role;
import com.wipro.domain.role.RoleRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.any;

class RoleCreateServiceTest {

    @Test
    public void save_existingId_success(){
        new TestSpec()
                .given_RoleCreateRequest_with_existingId()
                .given_roleRepository_save_return_validRole()
                .when_save()
                .then_ValidRoleCreateResponse_isReturned();
    }

    @Test
    public void save_nonexistingId_error(){
        new TestSpec()
                .given_RoleCreateRequest_with_nonexistingId()
                .given_roleRepository_save_return_validRole()
                .when_save()
                .then_role_is_null();
    }

    static class TestSpec {

        @InjectMocks
        RoleCreateService roleCreateService;

        @Mock
        RoleRepository repository;

        Role role;
        Role roleInserted;

        TestSpec(){
            MockitoAnnotations.initMocks(this);
        }

        public TestSpec given_RoleCreateRequest_with_existingId(){
            role = new Role();
            role.setId(1L);
            role.setName("Admin");
            return this;
        }

        public TestSpec given_RoleCreateRequest_with_nonexistingId(){
            role = new Role();
            role.setName("Admin");
            return this;
        }

        public TestSpec given_roleRepository_save_return_validRole(){
            BDDMockito.given(repository.save(any(Role.class))).willReturn(role);
            return this;
        }

        public TestSpec when_save() throws IllegalArgumentException{
            roleInserted = roleCreateService.insert(role);
            return this;
        }

        public TestSpec then_ValidRoleCreateResponse_isReturned(){
            Assert.assertNotNull(roleInserted);
            return this;
        }

        public TestSpec then_role_is_null(){
            Assert.assertNull(roleInserted.getId());
            return this;
        }

    }

}