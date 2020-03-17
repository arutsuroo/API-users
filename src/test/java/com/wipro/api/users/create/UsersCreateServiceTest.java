package com.wipro.api.users.create;

import com.wipro.api.roles.detail.RoleDetailService;
import com.wipro.domain.users.User;
import com.wipro.domain.users.UserRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest
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

    class TestSpec {

        User user;
        User returnedUser;
        Exception exception;

        @InjectMocks
        UsersCreateService usersCreateService;

        @Mock
        UserRepository repository;

        @Mock
        RoleDetailService service;

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

        public TestSpec given_userRepository_save_return_validUser() {
            BDDMockito.given(repository.save(this.user)).willReturn(user);
            return this;
        }

        public TestSpec when_save(){
            try {
                returnedUser = usersCreateService.insert(user, 1L);
            } catch (Exception e){
                this.exception = e;
            }
            return this;
        }

        public TestSpec then_no_validation_errors() {
            assertNotNull(returnedUser);
            return this;
        }


        public TestSpec then_exception_notThrown() throws Exception {
            if (exception != null) {
                throw exception;
            }
            return this;
        }


    }

}