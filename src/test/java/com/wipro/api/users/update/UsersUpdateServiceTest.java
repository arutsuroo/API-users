package com.wipro.api.users.update;

import com.wipro.domain.users.User;
import com.wipro.domain.users.UserRepository;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

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

    class TestSpec {

        @InjectMocks
        UsersUpdateService service;

        @Mock
        UserRepository repository;

        User user;
        User userEmail;
        User userUpdated;

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
            userEmail.setId(12L);
            userEmail.setEmail("joao@email.com");
            return this;
        }

        public TestSpec when_save(){
            userUpdated = service.update(1L, userEmail);
            return this;
        }

        public TestSpec then_validUsersUpdateResponse_isReturned(){
            assertThat(userUpdated.getEmail()).isEqualTo(userEmail.getEmail());
            return this;
        }




    }

}