package com.wipro.api.roles.create;

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

import static org.mockito.Matchers.any;


@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class RoleCreateServiceTest {

    @Test
    public void test_insert_role_success(){
        new TestSpec()
                .given_use_default_role_set_name("Admin")
                .given_repository_return_role()
                .when_call_insert()
                .then_role_not_null();
    }

    @Test
    public void test_insert_role_without_name_fail(){
        new TestSpec()
                .given_use_default_role_set_name(null)
                .given_repository_return_role()
                .when_call_insert()
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

        public TestSpec then_role_is_null(){
            Assert.assertNull(roleInserted.getName());
            return this;
        }

    }

}