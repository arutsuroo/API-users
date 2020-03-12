package com.wipro.api.users.update;

import com.wipro.domain.users.User;
import com.wipro.domain.users.UserRepository;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.BDDMockito.given;

public class UsersUpdateServiceTest {

    @Test
    public void test_update_user_success(){
        new TestSpec()
                .given_user_stored_in_base()
                .given_user_with_email()
                .when_call_update()
                .then_update_was_done();
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

        public TestSpec given_user_stored_in_base(){
            user = new User();
            user.setId(1L);
            user.setFirstName("First Name");
            user.setLastName("Last Name");
            user.setEmail("email@email.com");
            user.setBirthDate(LocalDate.of(2020, 1, 12));
            given(repository.findById(userUpdated.getId())).willReturn(Optional.of(user));
            return this;
        }

        public TestSpec given_user_with_email(){
            userEmail = new User();
            userEmail.setId(12L);
            userEmail.setEmail("joao@email.com");
            return this;
        }

        public TestSpec when_call_update(){
            userUpdated = service.update(1L, userEmail);
            return this;
        }

        public TestSpec then_update_was_done(){
            assertThat(userUpdated.getEmail()).isEqualTo(userEmail.getEmail());
            return this;
        }




    }

}