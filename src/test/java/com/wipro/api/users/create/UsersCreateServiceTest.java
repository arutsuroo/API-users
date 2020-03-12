package com.wipro.api.users.create;

import com.wipro.api.roles.detail.RoleDetailService;
import com.wipro.domain.users.User;
import com.wipro.domain.users.UserRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import javax.validation.ConstraintViolation;
import java.time.LocalDate;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Matchers.any;

class UsersCreateServiceTest {

    @Test
    public void save_existingId_success() throws Exception {
        new TestSpec()
                .given_UserCreateRequest_with_existingId()
                .given_userRepository_save_return_validUser()
                .when_save()
                .then_exception_notThrown()
                .then_no_validation_errors();
    }

    @Test
    public void save_nonexistingId_error() throws Exception{
        new TestSpec()
                .given_UserCreateRequest_with_nonexistingId()
                .given_userRepository_save_return_validUser()
                .when_save()
                .then_exception_notThrown()
                .then_validation_fails_with_message("NullPointerException");
    }


    class TestSpec {

        Exception exception;
        private Set<ConstraintViolation<User>> violations;
        User user;
        User userInserted;

        @InjectMocks
        UsersCreateService usersCreateService;

        @Mock
        UserRepository repository;

        @Mock
        RoleDetailService roleDetailService;

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

        public TestSpec when_save() throws Exception {
            userInserted = usersCreateService.insert(user, 1L);
            return this;
        }

        public TestSpec then_no_validation_errors() {
            Assert.assertNotNull(userInserted);
            return this;
        }

        public TestSpec then_user_is_null(){
            Assert.assertNull(userInserted.getId());
            return this;
        }

        public TestSpec then_exception_notThrown() throws Exception {
            if (exception != null) {
                throw exception;
            }
            return this;
        }

        public TestSpec then_validation_fails_with_message(String msg) {
            violations.forEach(System.out::println);
            //assertThat(violations).isInstanceOf(NullPointerException.class);
            return this;
        }

    }

}