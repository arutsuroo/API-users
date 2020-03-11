package com.wipro.api.users.detail;

import com.wipro.common.exceptions.ResourceNotFoundException;
import com.wipro.domain.users.User;
import com.wipro.domain.users.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.BDDMockito.*;

class UsersDetailServiceTest {

    @Test
    public void findById_existentId_success() {
        new TestSpec()
                .given_UserDetailResques_with_existentId()
                .given_userRepository_findById_return_validUser()
                .when_findById()
                .then_no_exception_thrown()
                .then_validUserDetailResponse_isReturned();
    }

    @Test
    public void findById_nonexistentId_error(){
        new TestSpec()
                .given_UserDetailResques_with_nonexistentId()
                .given_userRepository_findById_return_null() // or exception like no data found
                .when_findById()
                .then_exception_thrown_with_message("Resource Not Found Exception");
    }

    class TestSpec {

        @Mock
        UserRepository repository;

        @InjectMocks
        UsersDetailService service;

        User user;

        TestSpec(){
            MockitoAnnotations.initMocks(this);
        }

        public TestSpec given_UserDetailResques_with_existentId(){
            user = new User();
            user.setId(1L);
            user.setFirstName("First Name");
            user.setLastName("Last Name");
            user.setEmail("email@email.com");
            user.setBirthDate(LocalDate.of(2020, 1, 12));
            return this;
        }

        public TestSpec given_UserDetailResques_with_nonexistentId(){
            user = new User();
            user.setId(1L);
            user.setFirstName("First Name");
            user.setLastName("Last Name");
            user.setEmail("email@email.com");
            user.setBirthDate(LocalDate.of(2020, 1, 12));
            return this;
        }

        public TestSpec when_findById(){
            service.findById(user.getId());
            return this;
        }

        public TestSpec then_no_exception_thrown(){
            return this;
        }

        public TestSpec given_userRepository_findById_return_validUser(){
            given(repository.findById(user.getId())).willReturn(Optional.of(user));
            return this;
        }

        public TestSpec given_userRepository_findById_return_null(){
            given(repository.findById(1L)).willReturn(Optional.empty());
            return this;
        }

        public TestSpec then_validUserDetailResponse_isReturned(){
            then(service.findById(user.getId())).should().getId();
            return this;
        }

        public TestSpec then_exception_thrown_with_message(String e){
            fail("Should throw " + e);
            return this;
        }
    }
}