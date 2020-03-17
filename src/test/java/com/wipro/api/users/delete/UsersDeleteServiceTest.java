package com.wipro.api.users.delete;

import com.wipro.api.roles.detail.RoleDetailService;
import com.wipro.domain.users.User;
import com.wipro.domain.users.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest
class UsersDeleteServiceTest {

    @Test
    public void delete_existingId_success(){
        new TestSpec()
                .given_UserDeleteService_with_existingId()
                .given_userRepository_delete_return_validUser()
                .when_delete()
                .then_no_delete_errors();
    }

    class TestSpec {

        @Mock
        UserRepository repository;

        @Mock
        UsersDeleteService usersDeleteService;

        @Mock
        RoleDetailService service;

        User user;

        TestSpec(){
            MockitoAnnotations.initMocks(this);
        }

        public TestSpec given_UserDeleteService_with_existingId(){
            user = new User();
            user.setId(1L);
            user.setFirstName("First Name");
            user.setLastName("Last Name");
            user.setEmail("email@email.com");
            user.setBirthDate(LocalDate.of(2020, 1, 12));
            user.setRoles(null);
            return this;
        }

        public TestSpec given_userRepository_delete_return_validUser() {
            BDDMockito.given(repository.save(any(User.class))).willReturn(user);
            return this;
        }

        public TestSpec when_delete(){
            usersDeleteService.delete(user.getId());
            return this;
        }

        public TestSpec then_no_delete_errors(){
            verify(usersDeleteService).delete(user.getId());
            return this;
        }
    }

}