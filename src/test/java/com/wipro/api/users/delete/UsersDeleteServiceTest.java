package com.wipro.api.users.delete;

import com.wipro.api.roles.detail.RoleDetailService;
import com.wipro.api.users.create.UsersCreateService;
import com.wipro.domain.users.User;
import com.wipro.domain.users.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDate;
import static org.mockito.Matchers.any;

class UsersDeleteServiceTest {

    @Test
    public void delete_existingId_success(){
        new TestSpec()
                .given_UserDeleteService_with_existingId()
                .given_userRepository_delete_return_validUser()
                .when_delete()
                .then_delete_return_success();
    }


    class TestSpec {

        @Mock
        UserRepository repository;

        @InjectMocks
        UsersCreateService usersCreateService;

        @Mock
        UsersDeleteService usersDeleteService;

        @Mock
        RoleDetailService service;

        User user;
        User userInserted;

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
            return this;
        }

        public TestSpec given_userRepository_delete_return_validUser() {
            BDDMockito.given(repository.save(any(User.class))).willReturn(user);
            return this;
        }

        public TestSpec when_delete(){
            User user = this.user;
            usersDeleteService.delete(1L);
            return this;
        }

        public TestSpec then_delete_return_success(){
            //then(userDeleteService.delete(role.getId())).should()
            return this;
        }
    }

}