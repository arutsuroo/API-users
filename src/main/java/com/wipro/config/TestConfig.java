package com.wipro.config;

import com.wipro.domain.users.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {


    @Override
    public void run(String... args) throws Exception {
        User u1 = new User("trillian", "Tricia","McMillan", LocalDate.parse("1994-01-01"),"tricia42@dolphins.com");
        User u2 = new User("trillian2", "Tricia2", "McMillan2", LocalDate.parse("1994-01-01"), "tricia42_2@dolphins.com");
    }
}
