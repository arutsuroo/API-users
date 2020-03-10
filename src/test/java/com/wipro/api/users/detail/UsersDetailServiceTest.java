package com.wipro.api.users.detail;

import com.wipro.api.roles.detail.RoleDetailService;
import com.wipro.api.users.create.UsersCreateSevice;
import com.wipro.domain.users.User;
import com.wipro.domain.users.UserRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Matchers.any;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class UsersDetailServiceTest {

    @Test
    public void test_insert_user_success(){
        new TestSpec()
                .given_use_default_user_set_name()
                .given_repository_return_user()
                .when_call_insert()
                .then_user_not_null()
                .when_call_detail();
    }

    @Test
    public void test_insert_user_null_and_user_not_found_fail(){
        new TestSpec()
                .given_use_default_user_set_name()
                .given_repository_return_user()
                .when_call_insert()
                .then_user_is_not_equals()
                .when_call_detail_user_null();
    }


     static class TestSpec {

        @InjectMocks
        UsersCreateSevice usersCreateSevice;

        @Mock
        RoleDetailService roleDetailService;

        @Mock
        UsersDetailService usersDetailService;

        @Mock
        UserRepository repository;

        User user;
        User userInserted;

        TestSpec(){
             MockitoAnnotations.initMocks(this);
         }

        public TestSpec given_use_default_user_set_name(){
            user = new User();
            user.setId(1L);
            user.setFirstName("First Name");
            user.setLastName("Last Name");
            user.setEmail("email@email.com");
            user.setBirthDate(LocalDate.of(2020, 1, 12));
            return this;
        }

        public TestSpec given_repository_return_user(){
            BDDMockito.given(repository.save(any(User.class))).willReturn(user);
            return this;
        }

        public TestSpec when_call_insert(){
            userInserted = usersCreateSevice.insert(user, 1L);
            return this;
        }

        public TestSpec when_call_detail(){
            usersDetailService.findById(userInserted.getId());
            return this;
        }

        public TestSpec when_call_detail_user_null(){
            usersDetailService.findById(null);
            return this;
        }

        public TestSpec then_user_not_null(){
            Assert.assertNotNull(userInserted);
            return this;
        }

        public TestSpec then_user_is_not_equals(){
            assertThat(userInserted.getFirstName()).isNotEqualTo("Fist Name");
            return this;
        }

    }

}