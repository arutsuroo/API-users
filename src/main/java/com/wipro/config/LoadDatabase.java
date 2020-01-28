package com.wipro.config;

import com.wipro.domain.users.User;
import com.wipro.domain.users.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;

@Configuration
@Slf4j
class LoadDatabase {

SimpleDateFormat sdt = new SimpleDateFormat("dd/MM/yyyy");

    @Bean
    CommandLineRunner initDatabase(UserRepository repository){
        return args -> {
            log.info("Preloading " + repository.save(new User("trillian", "Tricia",
                    "McMillan",
                    sdt.parse("01/01/1994"),
                    "tricia42@dolphins.com")));
            log.info("Preloading " + repository.save(new User("trillian2", "Tricia2", "McMillan2", sdt.parse("01/01/1994"), "tricia42_2@dolphins.com")));
        };
    }
}
