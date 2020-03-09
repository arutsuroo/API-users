package com.wipro.api.users.create;

import com.wipro.api.roles.detail.RoleDetailService;
import com.wipro.domain.role.Role;
import com.wipro.domain.users.User;
import com.wipro.domain.users.UserRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class UsersCreateSeviceTest {

    @Test
    public void test_insert_without_role_success(){
        new TestSpec()
                .given_use_default_user()
                .given_repository_return_user()
                .when_call_insert()
                .then_user_not_null();
    }

    @Test
    public void test_insert_with_role_null_success(){
        new TestSpec()
                .given_use_default_user()
                .given_repository_return_user()
                .when_call_insert()
                .then_user_not_null();
    }

    @Test
    public void test_insert_firstName_is_equal_success(){
        new TestSpec()
                .given_use_default_user()
                .given_useDefaultUser_setFirstName("Test Name")
                .given_repository_return_user()
                .when_call_insert()
                .then_user_not_null()
                .then_user_firstName_matches("Test Name");
    }

    private Role getRoleMock() {
        Role role = mock(Role.class);
        role.setName("Test Role");
        role.setId(1L);
        return role;
    }

    class TestSpec {

        @Mock
        RoleDetailService service;

        @Mock
        UserRepository repository;

        @InjectMocks
        UsersCreateSevice usersCreateSevice;

        User user;
        User userInserted;

        TestSpec() {MockitoAnnotations.initMocks(this); }

        public TestSpec given_use_default_user() {
            user = new User();
            user.setFirstName("First Name");
            user.setLastName("Last Name");
            user.setEmail("email@email.com");
            user.setBirthDate(LocalDate.of(2020, 1, 12));

            return this;
        }

        public TestSpec given_useDefaultUser_setFirstName(String firstName) {
            user.setFirstName(firstName);
            return this;
        }

        public TestSpec given_repository_return_user() {
            BDDMockito.given(repository.save(any(User.class))).willReturn(user);
            return this;
        }

        public TestSpec when_call_insert() {
            userInserted = usersCreateSevice.insert(user, 1L);
            return this;
        }

        public TestSpec then_user_not_null() {
            Assert.assertNotNull(userInserted);
            return this;
        }

        public TestSpec then_user_firstName_matches(String firstName) {
            Assert.assertEquals(firstName, user.getFirstName());
            return this;
        }

        public TestSpec test_insert_user_null(){
            try{
                usersCreateSevice.insert(null, 1L);
            }
            catch (NullPointerException e){
                Assert.assertNotNull(e);
            }
            return this;
        }

        public TestSpec test_insert_with_role_success(){
            Role role = getRoleMock();
            Mockito.when(service.findById(any(long.class))).thenReturn(role);
            User user = this.user;
            Mockito.when(repository.save(any(User.class))).thenReturn(user);

            User userInserted = usersCreateSevice.insert(user, 1L);

            Assert.assertNotNull(userInserted);
            Assert.assertNotNull(userInserted.getRoles());
            return this;
        }




    }

}