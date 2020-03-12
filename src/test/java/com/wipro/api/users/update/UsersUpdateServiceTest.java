package com.wipro.api.users.update;

import com.wipro.api.roles.update.RoleUpdateServiceTest;
import com.wipro.common.exceptions.ResourceNotFoundException;
import com.wipro.domain.role.Role;
import com.wipro.domain.users.User;
import com.wipro.domain.users.UserRepository;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.validation.ConstraintViolation;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.BDDMockito.given;

public class UsersUpdateServiceTest {

    @Test
    public void getOne_existingId_success(){
        new TestSpec()
                .given_UsersUpdateRequest_with_existingId()
                .given_userRepository_findById_return_validUser()
                .when_save()
                .then_validUsersUpdateResponse_isReturned();
    }

//    @Test
//    public void getOne_unexistingId_UserNotFound_error(){
//        new TestSpec()
//                .given_UsersUpdateRequest_with_existingId()
//                .given_userRepository_findById_return_validUser()
//                .
//    }

    class TestSpec {

        User user;
        User userEmail;
        User userUpdated;
        Exception exception;
        private Set<ConstraintViolation<User>> violations;

        @InjectMocks
        UsersUpdateService service;

        @Mock
        UserRepository repository;

        TestSpec(){
            MockitoAnnotations.initMocks(this);
        }

        public TestSpec given_UsersUpdateRequest_with_existingId(){
            user = new User();
            user.setId(1L);
            user.setFirstName("First Name");
            user.setLastName("Last Name");
            user.setEmail("email@email.com");
            user.setBirthDate(LocalDate.of(2020, 1, 12));
            given(repository.findById(userUpdated.getId())).willReturn(Optional.of(user));
            return this;
        }

        public TestSpec given_userRepository_findById_return_validUser(){
            userEmail = new User();
            return this;
        }

        public TestSpec when_save(){
            try {
                userUpdated = service.update(1L, userEmail);
            } catch (ResourceNotFoundException e){
                this.exception = e;
            }
            return this;
        }

        public TestSpec then_validUsersUpdateResponse_isReturned(){
            assertThat(userUpdated).isNotNull();
            assertThat(userUpdated.getEmail()).isEqualTo(userEmail.getEmail());
            return this;
        }

        public TestSpec given_user_with_new_email(){
            userEmail = new User();
            userEmail.setId(12L);
            userEmail.setEmail("drauzio@email.com");
            return this;
        }




    }

}