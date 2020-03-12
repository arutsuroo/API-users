package com.wipro.api.users.list;

import com.wipro.domain.users.UserRepository;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;


public class UsersListServiceTest {

    @Test
    public void findAll_success(){
        new TestSpec()
                .when_findAll()
                .then_validUsersListResponse_isReturned();
    }


    class TestSpec {

        @Mock
        UsersListService usersListService;

        @Mock
        UserRepository repository;

        TestSpec(){
            MockitoAnnotations.initMocks(this);
        }

        public TestSpec when_findAll(){
             usersListService.findAll();
             return this;
        }

        public TestSpec then_validUsersListResponse_isReturned(){
            verify(usersListService).findAll();
            return this;
        }


    }

}