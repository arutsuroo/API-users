package com.wipro.api.users.create;

import com.wipro.api.roles.detail.RoleDetailService;
import com.wipro.domain.users.User;
import com.wipro.domain.users.UserRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.time.LocalDate;

import static org.mockito.Matchers.any;

class UsersCreateServiceTest {

    @Test
    public void save_existingId_success(){
        new TestSpec()
                .given_UserCreateRequest_with_existingId()
                .given_userRepository_save_return_validUser()
                .when_save()
                .then_ValidUserCreateResponse_isReturned();
    }

    @Test
    public void save_nonexistingId_error(){
        new TestSpec()
                .given_UserCreateRequest_with_nonexistingId()
                .given_userRepository_save_return_validUser()
                .when_save()
                .then_user_is_null();
    }


    class TestSpec {

        @InjectMocks
        UsersCreateService usersCreateService;

        @Mock
        UserRepository repository;

        @Mock
        RoleDetailService roleDetailService;

        User user;
        User userInserted;

        TestSpec() {MockitoAnnotations.initMocks(this); }

        public TestSpec given_UserCreateRequest_with_existingId() {
            user = new User();
            user.setId(1L);
            user.setFirstName("First Name");
            user.setLastName("Last Name");
            user.setEmail("email@email.com");
            user.setBirthDate(LocalDate.of(2020, 1, 12));

            return this;
        }

        public TestSpec given_UserCreateRequest_with_nonexistingId() {
            user = new User();
            user.setFirstName("First Name");
            user.setLastName("Last Name");
            user.setEmail("email@email.com");
            user.setBirthDate(LocalDate.of(2020, 1, 12));

            return this;
        }

        public TestSpec given_userRepository_save_return_validUser() {
            BDDMockito.given(repository.save(any(User.class))).willReturn(user);
            return this;
        }

        public TestSpec when_save() {
            userInserted = usersCreateService.insert(user, 1L);
            return this;
        }

        public TestSpec then_ValidUserCreateResponse_isReturned() {
            Assert.assertNotNull(userInserted);
            return this;
        }

        public TestSpec then_user_is_null(){
            Assert.assertNull(userInserted.getId());
            return this;
        }

    }

}