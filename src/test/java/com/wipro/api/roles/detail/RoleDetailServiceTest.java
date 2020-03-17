package com.wipro.api.roles.detail;

import com.wipro.common.exceptions.ResourceNotFoundException;
import com.wipro.domain.role.Role;
import com.wipro.domain.role.RoleRepository;
import org.junit.jupiter.api.Test;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest
class RoleDetailServiceTest {

    @Test
    public void findById_existentId_success(){
        new TestSpec()
                .given_RoleDetailRequest_with_existentId()
                .given_roleRepository_findById_return_validRole()
                .when_findById()
                .then_no_exception_thrown()
                .then_validRoleDetailResponse_isReturned();
    }

    @Test
    public void findById_unexistentId_error() throws ResourceNotFoundException{
        new TestSpec()
                .given_RoleDetailRequest_with_nonexistentId()
                .given_roleRepository_findById_return_null()
                .when_findById()
                .then_validation_fails_with_ResourceNotFound();
    }

    class TestSpec{

        Role role;
        Role roleDetail;
        Exception exception;

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
            role.setId(80L);
            role.setName("Admin");
            return this;
        }

        public TestSpec given_roleRepository_findById_return_validRole(){
            given(repository.findById(role.getId())).willReturn(Optional.of(role));
            return this;
        }

        public TestSpec when_findById() throws ResourceNotFoundException{
            try {
                roleDetail = roleDetailService.findById(role.getId());
            } catch (Exception e){
                this.exception = e;
            }
            return this;
        }

        public TestSpec then_no_exception_thrown(){
            assertThat(exception).isNull();
            return this;
        }

        public TestSpec given_roleRepository_findById_return_null(){
            given(repository.findById(80L)).willReturn(Optional.empty());
            return this;
        }


        public TestSpec then_validRoleDetailResponse_isReturned() {
            assertThat(roleDetail).isNotNull();
            return this;
        }

        public TestSpec then_validation_fails_with_ResourceNotFound() {
            assertThat(exception).isInstanceOf(ResourceNotFoundException.class);
            return this;
        }
    }


}