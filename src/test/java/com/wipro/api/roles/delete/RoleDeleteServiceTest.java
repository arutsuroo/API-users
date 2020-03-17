package com.wipro.api.roles.delete;

import com.wipro.domain.role.Role;
import com.wipro.domain.role.RoleRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;


import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest
class RoleDeleteServiceTest {

    @Test
    public void delete_existingId_success(){
        new TestSpec()
                .given_RoleDeleteService_with_existingId()
                .given_roleRepository_findById_return_validRole()
                .when_delete()
                .then_no_delete_errors();
    }


    class TestSpec {

        @Mock
        RoleRepository repository;

        @InjectMocks
        RoleDeleteService roleDeleteService;

        Role role;

        TestSpec(){
            MockitoAnnotations.initMocks(this);
        }

        public TestSpec given_RoleDeleteService_with_existingId(){
            role = new Role();
            role.setId(1L);
            role.setName("Admin");
            return this;
        }

        public TestSpec given_roleRepository_findById_return_validRole(){
            BDDMockito.given(repository.save(any(Role.class))).willReturn(role);
            return this;
        }

        public TestSpec when_delete(){
            roleDeleteService.delete(role.getId());
            return this;
        }

        public TestSpec then_no_delete_errors(){
            verify(repository).deleteById(role.getId());
            return this;
        }



    }

}