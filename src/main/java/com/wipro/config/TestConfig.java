package com.wipro.config;

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
        User u1 = new User("trillian", "Tricia","McMillan", LocalDate.parse("1994-01-01"),"tricia42@dolphins.com");
        User u2 = new User("trillian2", "Tricia2", "McMillan2", LocalDate.parse("1994-01-01"), "tricia42_2@dolphins.com");

        userRepository.saveAll(Arrays.asList(u1,u2));
    }
}
