package com.wipro.config;

import com.wipro.domain.users.User;
import com.wipro.domain.users.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

@Configuration
@Slf4j
class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(UserRepository repository){
        return args -> {
            log.info("Preloading " + repository.save(new User("trillian", "Tricia","McMillan", LocalDate.parse("1994-01-01"),"tricia42@dolphins.com")));
            log.info("Preloading " + repository.save(new User("trillian2", "Tricia2", "McMillan2", LocalDate.parse("1994-01-01"), "tricia42_2@dolphins.com")));
        };
    }
}
