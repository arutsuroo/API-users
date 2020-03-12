package com.wipro.api.roles.detail;

import com.wipro.common.exceptions.ResourceNotFoundException;
import com.wipro.domain.role.Role;
import com.wipro.domain.role.RoleRepository;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.validation.ConstraintViolation;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

class RoleDetailServiceTest {

    @Test
    public void findById_existentId_success() throws Exception{
        new TestSpec()
                .given_RoleDetailRequest_with_existentId()
                .given_roleRepository_findById_return_validRole()
                .when_findById()
                .then_no_exception_thrown()
                .then_validRoleDetailResponse_isReturned();
    }

    @Test
    public void findById_unexistentId_error() throws Exception{
        new TestSpec()
                .given_RoleDetailRequest_with_nonexistentId()
                .given_roleRepository_findById_return_null()
                .when_findById()
                .then_exception_thrown()
                .then_validation_fails_with_message("Resource Not Found Exception");
    }

    class TestSpec{

        Role role;
        Role roleDetail;
        Exception exception;
        private Set<ConstraintViolation<Role>> violations;

        @InjectMocks
        RoleDetailService roleDetailService;

        @Mock
        RoleRepository repository;

        TestSpec(){
            MockitoAnnotations.initMocks(this);
        }

        public TestSpec given_RoleDetailRequest_with_existentId(){
            role = new Role();
            role.setId(1L);
            role.setName("Admin");
            return this;
        }

        public TestSpec given_RoleDetailRequest_with_nonexistentId(){
            role = new Role();
            role.setName("Admin");
            return this;
        }

        public TestSpec given_roleRepository_findById_return_validRole(){
            given(repository.findById(role.getId())).willReturn(Optional.of(role));
            return this;
        }

        public TestSpec when_findById(){
            try {
                roleDetail = roleDetailService.findById(role.getId());
            } catch (Exception e){
                this.exception = e;
            }

            return this;
        }

        public TestSpec then_no_exception_thrown(){
            assertThat(violations.size()).isEqualTo(0);
            return this;
        }

        public TestSpec given_roleRepository_findById_return_null(){
            given(repository.findById(1L)).willReturn(Optional.empty());
            return this;
        }

        public TestSpec then_exception_thrown(){
            assertThat(exception).isInstanceOf(NullPointerException.class);
            return this;
        }

        public TestSpec then_validRoleDetailResponse_isReturned() {
            assertThat(roleDetail).isNotNull();
            assertThat(roleDetail).isInstanceOf(Role.class);
            return this;
        }

        public TestSpec then_validation_fails_with_message(String msg) {
            violations.forEach(System.out::println);
            assertThat(violations).isInstanceOf(ResourceNotFoundException.class);
            return this;
        }




    }


}