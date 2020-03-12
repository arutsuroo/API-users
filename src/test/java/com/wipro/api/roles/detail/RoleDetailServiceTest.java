package com.wipro.api.roles.detail;

import com.wipro.common.exceptions.ResourceNotFoundException;
import com.wipro.domain.role.Role;
import com.wipro.domain.role.RoleRepository;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

class RoleDetailServiceTest {

    @Ignore
    public void findById_existentId_success(){
        new TestSpec()
                .given_RoleDetailRequest_with_existentId()
                .given_roleRepository_findById_return_validRole()
                .when_findById()
                .then_no_exception_thrown()
                .then_validRoleDetailResponse_isReturned();
    }

    @Ignore
    public void test_find_role_is_empty(){
        new TestSpec()
                .given_RoleDetailRequest_with_nonexistentId()
                .given_roleRepository_findById_return_null()
                .when_findById()
                .then_exception_thrown_with_message("Resource Not Found Exception");
    }

    class TestSpec{

        @InjectMocks
        RoleDetailService roleDetailService;

        @Mock
        RoleRepository repository;

        Role role;
        Role roleDetail;

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
            roleDetail = roleDetailService.findById(role.getId());
            Exception exception = assertThrows(ResourceNotFoundException.class, ()->{ Integer.parseInt("1a");});
            String expectedMessage = "Resource not found. Id " + role.getId();
            String actualMessage = exception.getMessage();

            assertTrue(actualMessage.contains(expectedMessage));
            return this;
        }

        public TestSpec then_no_exception_thrown(){
            return this;
        }

        public TestSpec then_validRoleDetailResponse_isReturned(){
            then(roleDetailService.findById(role.getId())).should().getId();
            return this;
        }

        public TestSpec given_roleRepository_findById_return_null(){
            given(repository.findById(1L)).willReturn(Optional.empty());
            return this;
        }

        public TestSpec then_exception_thrown_with_message(String e){
            fail("Should throw " + e);
            return this;
        }




    }


}