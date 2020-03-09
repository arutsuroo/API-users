package com.wipro.api.users.delete;

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
import org.springframework.dao.EmptyResultDataAccessException;

import java.time.LocalDate;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class UsersDeleteServiceTest {

    @Test
    public void test_insert_role_then_delete_success(){
        new TestSpec()
                .given_use_default_user_set_name("Admin")
                .given_repository_return_user()
                .when_call_insert()
                .then_user_not_null();
    }

    private Role getRoleMock() {
        Role role = mock(Role.class);
        role.setName("Test Role");
        role.setId(1L);
        return role;
    }

    class TestSpec {

        @Mock
        UserRepository repository;

        @Mock
        UsersCreateSevice usersCreateSevice;

        @InjectMocks
        UsersDeleteService usersDeleteService;

        User user;
        User userInserted;

        TestSpec(){
            MockitoAnnotations.initMocks(this);
        }

        public TestSpec given_use_default_user_set_name(String name){

            user = new User();

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

            Role role = getRoleMock();

            User user = this.user;
            User userInserted = usersCreateSevice.insert(user, 1L);

            Assert.assertNotNull(userInserted);
            Assert.assertNotNull(userInserted.getRoles());
            return this;
        }

        public TestSpec then_user_not_null(){
            Assert.assertNotNull(userInserted);
            return this;
        }

        public TestSpec when_call_delete() throws EmptyResultDataAccessException {
            usersDeleteService.delete(userInserted.getId());
            return this;
        }

        public TestSpec then_delete_was_called(){
            verify(repository).deleteById(userInserted.getId());
            return this;
        }

        public TestSpec then_delete_was_called_with_id_not_found(){
            verify(repository).deleteById(null);
            return this;
        }

    }

}