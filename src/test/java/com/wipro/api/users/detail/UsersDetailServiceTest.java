package com.wipro.api.users.detail;

import com.wipro.api.roles.detail.RoleDetailService;
import com.wipro.api.users.create.UsersCreateSevice;
import com.wipro.domain.role.Role;
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

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class UsersDetailServiceTest {


    @Test
    public void test_insert_user_success_then_find_user(){
        new TestSpec()
                .given_use_default_user_set_name()
                .given_repository_return_user()
                .when_call_insert()
                .then_user_not_null()
                .when_call_detail()
                .then_detail_was_called();
    }


    class TestSpec {

        @InjectMocks
        UsersCreateSevice usersCreateSevice;

        @Mock
        UsersDetailService usersDetailService;

        @Mock
        UserRepository repository;

        @Mock
        RoleDetailService roleDetailService;

        User user;
        User userInserted;

        TestSpec(){
            MockitoAnnotations.initMocks(this);
        }

        private Role getRoleMock() {
            Role role = mock(Role.class);
            role.setName("Test Role");
            role.setId(1L);
            return role;
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

        public TestSpec given_repository_return_user() {
            BDDMockito.given(repository.save(any(User.class))).willReturn(user);
            return this;
        }

        public TestSpec when_call_insert() throws IllegalArgumentException{
            userInserted = usersCreateSevice.insert(user, 1L);
            return this;
        }

        public TestSpec then_user_not_null(){
            Assert.assertNotNull(userInserted);
            return this;
        }

        public TestSpec when_call_detail(){
            usersDetailService.findById(userInserted.getId());
            return this;
        }

        public TestSpec then_detail_was_called(){
            verify(usersDetailService).findById(userInserted.getId());
            return this;
        }
    }

}