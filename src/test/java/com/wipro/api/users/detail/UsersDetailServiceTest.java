package com.wipro.api.users.detail;

import com.wipro.common.exceptions.ResourceNotFoundException;
import com.wipro.domain.users.User;
import com.wipro.domain.users.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Optional;


import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.*;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest
class UsersDetailServiceTest {

    @Test
    public void findById_existentId_success() throws Exception{
        new TestSpec()
                .given_UserDetailRequest_with_existentId()
                .given_userRepository_findById_return_validUser()
                .when_findById()
                .then_no_exception_thrown()
                .then_validUserDetailResponse_isReturned();
    }

    @Test
    public void findById_nonexistentId_error() throws ResourceNotFoundException{
        new TestSpec()
                .given_UserDetailRequest_with_nonexistentId()
                .given_userRepository_findById_return_null()
                .when_findById()
                .then_validation_fails_with_ResourceNotFound();
    }

    class TestSpec {

        @Mock
        UserRepository repository;

        @InjectMocks
        UsersDetailService service;

        User user;
        User userDetail;
        Exception exception;

        TestSpec(){
            MockitoAnnotations.initMocks(this);
        }

        public TestSpec given_UserDetailRequest_with_existentId(){
            user = new User();
            user.setId(1L);
            user.setFirstName("First Name");
            user.setLastName("Last Name");
            user.setEmail("email@email.com");
            user.setBirthDate(LocalDate.of(2020, 1, 12));
            return this;
        }

        public TestSpec given_UserDetailRequest_with_nonexistentId(){
            user = new User();
            user.setId(80L);
            user.setFirstName("First Name");
            user.setLastName("Last Name");
            user.setEmail("email@email.com");
            user.setBirthDate(LocalDate.of(2020, 1, 12));
            return this;
        }

        public TestSpec when_findById() throws ResourceNotFoundException{
            try{
                userDetail = service.findById(user.getId());
            } catch (Exception e){
                this.exception = e;
            }
            return this;
        }

        public TestSpec then_no_exception_thrown(){
            assertThat(exception).isNull();
            return this;
        }

        public TestSpec given_userRepository_findById_return_validUser(){
            given(repository.findById(user.getId())).willReturn(Optional.of(user));
            return this;
        }

        public TestSpec given_userRepository_findById_return_null(){
            given(repository.findById(80L)).willReturn(Optional.empty());
            return this;
        }

        public TestSpec then_validUserDetailResponse_isReturned(){
            assertThat(userDetail).isNotNull();
            assertThat(userDetail).isInstanceOf(User.class);
            return this;
        }

        public TestSpec then_validation_fails_with_ResourceNotFound(){
            assertThat(exception).isInstanceOf(ResourceNotFoundException.class);
            return this;
        }
    }
}