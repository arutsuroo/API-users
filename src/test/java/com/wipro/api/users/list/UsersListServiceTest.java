package com.wipro.api.users.list;

import com.wipro.domain.users.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class UsersListServiceTest {

    @Test
    public void test_list_user_success_and_list_is_not_null(){
        new TestSpec()
                .when_call_list()
                .then_list_was_called();
    }


    class TestSpec {

        @Mock
        UsersListService usersListService;

        @Mock
        UserRepository repository;

        TestSpec(){
            MockitoAnnotations.initMocks(this);
        }

        public TestSpec when_call_list(){
             usersListService.findAll();
             return this;
        }

        public TestSpec then_list_was_called(){
            verify(usersListService).findAll();
            return this;
        }


    }

}