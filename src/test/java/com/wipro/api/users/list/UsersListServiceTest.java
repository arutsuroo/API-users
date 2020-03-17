package com.wipro.api.users.list;

import com.wipro.domain.users.User;
import com.wipro.domain.users.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest
public class UsersListServiceTest {

    @Test
    public void findAll_success(){
        new TestSpec()
                .given_userRepository_findAll_return_validUser()
                .when_findAll()
                .then_validUsersListResponse_isReturned();
    }

    class TestSpec {

        List<User> user;
        Exception exception;

        @Mock
        UsersListService usersListService;

        @Mock
        UserRepository repository;

        TestSpec(){
            MockitoAnnotations.initMocks(this);
        }

        public TestSpec given_userRepository_findAll_return_validUser(){
            given(repository.findAll()).willReturn(Arrays.asList());
            return this;
        }

        public TestSpec when_findAll(){
            try {
                user = usersListService.findAll();
            } catch (Exception e){
                this.exception = e;
            }
            return this;
        }

        public TestSpec then_validUsersListResponse_isReturned(){
           assertNotNull(user);
           return this;
        }
    }
}