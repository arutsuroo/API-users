package com.wipro.api.roles.update;

import com.wipro.domain.role.Role;
import com.wipro.domain.role.RoleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest
public class RoleUpdateServiceTest {

    @Test
    public void getOne_existingId_success(){
        new TestSpec()
                .given_RoleUpdateRequest_with_existingId_toUpdate()
                .given_roleRepository_findById_return_valid_role()
                .when_save()
                .then_validRoleUpdateResponse_isReturned();
    }


    class TestSpec {

        Role role;
        Role roleName;
        Role roleUpdated;
        Exception exception;

        @InjectMocks
        RoleUpdateService service;

        @Mock
        RoleRepository repository;


        TestSpec(){
            MockitoAnnotations.initMocks(this);
        }

        public TestSpec given_RoleUpdateRequest_with_existingId_toUpdate(){
            roleName = new Role();
            roleName.setId(1L);
            roleName.setName("Admin");
            return this;
        }

        public TestSpec given_roleRepository_findById_return_valid_role(){
            role = new Role();
            role.setId(1L);
            role.setName("Developer");
            given(repository.findById(1L)).willReturn(Optional.of(role));
            given(repository.save(role)).willReturn(role);
            return this;
        }

        public TestSpec when_save() {
            try {
                roleUpdated = service.update(1L, role);
            } catch (Exception e){
                this.exception = e;
            }
            return this;
        }

        public TestSpec then_validRoleUpdateResponse_isReturned(){
            assertThat(roleUpdated).isNotNull();
            assertThat(roleUpdated.getName()).isNotEqualTo(roleName.getName());
            return this;
        }


    }

}