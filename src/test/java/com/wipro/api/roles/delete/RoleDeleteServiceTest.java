package com.wipro.api.roles.delete;

import com.wipro.api.roles.create.RoleCreateService;
import com.wipro.domain.role.Role;
import com.wipro.domain.role.RoleRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class RoleDeleteServiceTest {

    @Test
    public void test_insert_role_then_delete_success(){
        new TestSpec()
                .given_use_default_role_set_name("Admin")
                .given_repository_return_role()
                .when_call_insert()
                .then_role_not_null()
                .when_call_delete()
                .then_delete_was_called();
    }

    @Test
    public void test_insert_role_then_delete_fail(){
        new TestSpec()
                .given_use_default_role_set_name("Admin")
                .given_repository_return_role()
                .when_call_insert()
                .then_role_not_null()
                .when_call_delete()
                .then_delete_was_called_with_id_not_found();
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

        public TestSpec given_use_default_role_set_name(String name){
            role = new Role();
            role.setName(name);
            return this;
        }

        public TestSpec given_repository_return_role(){
            BDDMockito.given(repository.save(any(Role.class))).willReturn(role);
            return this;
        }

        public TestSpec when_call_insert() throws IllegalArgumentException{
            roleInserted = roleCreateService.insert(role);
            return this;
        }

        public TestSpec then_role_not_null(){
            Assert.assertNotNull(roleInserted);
            return this;
        }

        public TestSpec then_delete_was_called(){
            verify(repository).deleteById(roleInserted.getId());
            return this;
        }

        public TestSpec then_delete_was_called_with_id_not_found(){
            verify(repository).deleteById(null);
            return this;
        }

        public TestSpec when_call_delete() throws EmptyResultDataAccessException {
            roleDeleteService.delete(roleInserted.getId());
            return this;
        }

    }

}