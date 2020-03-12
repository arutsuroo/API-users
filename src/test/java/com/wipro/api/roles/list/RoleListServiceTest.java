package com.wipro.api.roles.list;

import com.wipro.domain.role.RoleRepository;
import lombok.Setter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolation;
import java.util.Arrays;
import java.util.Set;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.BDDMockito.*;

//@RunWith(SpringRunner.class)
//@ActiveProfiles("test")
//@SpringBootTest
public class RoleListServiceTest {

    @Test
    public void findAll_success(){
        new TestSpec()
                .given_roleRepository_findAll_return_validRole()
                .when_findAll()
                .then_validRoleListResponse_isReturned();
    }

    @Setter
    class Pojo {
        String roleStatus;
    }

    class TestSpec {

        Pojo pojo;
        Exception exception;
        private Set<ConstraintViolation<Pojo>> violations;

        @InjectMocks
        RoleListService roleListService;

        @Mock
        RoleRepository repository;

        TestSpec(){
            MockitoAnnotations.initMocks(this);
        }

        public TestSpec given_roleRepository_findAll_return_validRole(){
            given(repository.findAll()).willReturn(Arrays.asList());
            return this;
        }

        public TestSpec when_findAll(){
            try {
                roleListService.findAll();
            } catch (Exception e){
                this.exception = e;
            }
            return this;
        }

        public TestSpec then_validRoleListResponse_isReturned(){
            assertThat(violations.isEmpty());
            return this;
        }

    }

}