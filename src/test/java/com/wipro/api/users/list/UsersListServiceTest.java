package com.wipro.api.users.list;

import com.wipro.domain.users.User;
import com.wipro.domain.users.UserRepository;
import lombok.Setter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolation;
import java.util.Arrays;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

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

        Exception exception;
        private Set<ConstraintViolation<User>> violations;

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
            usersListService.findAll();
            return this;
        }

        public TestSpec then_validUsersListResponse_isReturned(){
            try {
                assertThat(violations.size()).isEqualTo(0);
            } catch (Exception e){
                this.exception = e;
            }
            return this;
        }
    }
}