package com.wipro.api.roles.detail;

import com.wipro.api.roles.create.RoleCreateService;
import com.wipro.domain.role.Role;
import com.wipro.domain.role.RoleRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class RoleDetailServiceTest {

    @Test
    public void test_find_role_success(){
        new TestSpec()
                .given_use_default_role_set_name("Admin")
                .then_role_is_returned();
    }

    @Test
    public void test_find_role_is_empty(){
        new TestSpec()
                .given_use_default_role_set_name("Admin")
                .then_role_is_empty();
    }

    class TestSpec{

        @InjectMocks
        RoleCreateService roleCreateService;

        @Mock
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

        public TestSpec then_role_is_returned(){
            given(repository.findById(role.getId())).willReturn(Optional.of(role));            return this;
        }

        public TestSpec then_role_is_empty(){
            given(repository.findById(1L)).willReturn(Optional.empty());
            return this;
        }


    }


}