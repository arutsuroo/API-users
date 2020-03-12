package com.wipro.api.roles.create;

import com.wipro.domain.role.Role;
import com.wipro.domain.role.RoleRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolation;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest
class RoleCreateServiceTest {

    @Test
    public void save_existingId_success() throws Exception{
        new TestSpec()
                .given_RoleCreateRequest_with_existingId()
                .given_roleRepository_save_return_validRole()
                .when_save()
                .then_exception_notThrown()
                .then_no_validation_errors();
    }

    @Test
    public void save_nonexistingId_fail() throws Exception {
        new TestSpec()
                .given_RoleCreateRequest_with_nonexistingId()
                .given_roleRepository_save_return_validRole()
                .when_save()
                .then_exception_notThrown()
                .then_validation_fails_with_message("NullPointerException");
    }

    static class TestSpec {

        Role role;
        Exception exception;
        private Set<ConstraintViolation<Role>> violations;

        @Mock
        RoleCreateService roleCreateService;

        @Mock
        RoleRepository repository;

        TestSpec(){
            MockitoAnnotations.initMocks(this);
        }

        public TestSpec given_RoleCreateRequest_with_existingId(){
            role = new Role();
            role.setId(1L);
            role.setName("Admin");
            return this;
        }

        public TestSpec given_RoleCreateRequest_with_nonexistingId(){
            role = new Role();
            role.setName("Admin");
            return this;
        }

        public TestSpec given_roleRepository_save_return_validRole(){
            BDDMockito.given(repository.save(this.role)).willReturn(role);
            return this;
        }

        public TestSpec then_exception_notThrown() throws Exception {
            if (exception != null) {
                throw exception;
            }
            return this;
        }

        public TestSpec then_exception_thrown() {
            assertThat(exception).isNotNull();
            return this;
        }

        public TestSpec when_save(){
            try {
                roleCreateService.insert(role);
            } catch (Exception e){
                this.exception = e;
            }

            return this;
        }

        public TestSpec then_no_validation_errors(){
            assertThat(violations.size()).isEqualTo(0);
            return this;
        }

        public TestSpec then_validation_fails_with_message(String msg) {
            violations.forEach(System.out::println);
            assertThat(violations).isInstanceOf(NullPointerException.class);
            return this;
        }
    }

}