package com.wipro.api.users.update;

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

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest
public class UsersUpdateServiceTest {

    @Test
    public void getOne_existingId_success(){
        new TestSpec()
                .given_UsersUpdateRequest_with_existingId_toUpdate()
                .given_userRepository_findById_return_validUser()
                .when_save()
                .then_validUsersUpdateResponse_isReturned();
    }

    class TestSpec {

        User user;
        User userEmail;
        User userUpdated;
        Exception exception;

        @InjectMocks
        UsersUpdateService service;

        @Mock
        UserRepository repository;

        TestSpec(){
            MockitoAnnotations.initMocks(this);
        }

        public TestSpec given_UsersUpdateRequest_with_existingId_toUpdate(){
            userEmail = new User();
            userEmail.setId(1L);
            userEmail.setEmail("drauziovarela@globo.com");
            return this;
        }


        public TestSpec given_userRepository_findById_return_validUser(){

            user = new User();
            user.setId(1L);
            user.setFirstName("First Name");
            user.setLastName("Last Name");
            user.setEmail("email@email.com");
            user.setBirthDate(LocalDate.of(2020, 1, 12));
            given(repository.findById(1L)).willReturn(Optional.of(user));
            given(repository.save(user)).willReturn(user);
            return this;
        }

        public TestSpec when_save(){
            try {
                userUpdated = service.update(userEmail.getId(), userEmail);
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