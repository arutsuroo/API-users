package com.wipro.api.roles.detail;


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

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class RoleDetailServiceTest {

    @Test
    public void test_insert_role_success(){
        new TestSpec()
                .given_use_default_role_set_name("Admin")
                .given_repository_return_role()
                .when_call_insert()
                .then_role_not_null()
                .when_call_detail()
                .then_detail_was_called();
    }

    static class TestSpec{

        @InjectMocks
        RoleCreateService roleCreateService;

        @InjectMocks
        RoleDetailService roleDetailService;

        @Mock
        RoleRepository repository;

        Role role;
        Role roleInserted;

        TestSpec(){
            MockitoAnnotations.initMocks(this);
        }

        public TestSpec given_use_default_role_set_name(String name){
            role = new Role();
            role.setId(1L);
            role.setName(name);
            return this;
        }

        public TestSpec given_repository_return_role(){
            BDDMockito.given(repository.save(any(Role.class))).willReturn(role);
            return this;
        }

        public TestSpec when_call_insert(){
            roleInserted = roleCreateService.insert(role);
            return this;
        }

        public TestSpec when_call_detail(){
            roleDetailService.findById(roleInserted.getId());
            return this;
        }

        public TestSpec then_role_not_null(){
            Assert.assertNotNull(roleInserted);
            return this;
        }

        public TestSpec then_detail_was_called(){
            verify(repository).findById(roleInserted.getId());
            return this;
        }



    }


}