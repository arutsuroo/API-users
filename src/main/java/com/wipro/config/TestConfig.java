package com.wipro.config;

import com.wipro.api.users.common.UsersDto;
import com.wipro.domain.role.Role;
import com.wipro.domain.users.User;
import com.wipro.domain.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;
import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {

//        Role role1 = new Role();
//        role1.setName("admin");
//
//        Role role2 = new Role();
//        role2.setName("employee");
//
//        User u1 = new User("trillian", "Tricia","McMillan", LocalDate.parse("1994-01-01"),"tricia42@dolphins.com", role1);
//        User u2 = new User("trillian2", "Tricia2", "McMillan2", LocalDate.parse("1994-01-01"), "tricia42_2@dolphins.com", role2);
//
//        userRepository.saveAll(Arrays.asList(u1,u2));
    }
}
