package com.wipro.api.roles.list;

import com.wipro.domain.role.RoleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class RoleListServiceTest {

    @Test
    public void test_list_role_success_and_list_is_not_null(){
        new TestSpec()
                .when_call_list()
                .then_list_was_called();
    }


    class TestSpec {

        @Mock
        RoleListService roleListService;

        @Mock
        RoleRepository repository;

        TestSpec(){
            MockitoAnnotations.initMocks(this);
        }

        public TestSpec when_call_list(){
            roleListService.findAll();
            return this;
        }

        public TestSpec then_list_was_called(){
            verify(roleListService).findAll();
            return this;
        }

    }

}