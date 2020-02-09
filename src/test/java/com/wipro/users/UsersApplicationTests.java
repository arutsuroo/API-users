package com.wipro.users;

import com.wipro.api.users.create.UserCreateRestController;
import com.wipro.domain.users.User;
import com.wipro.domain.users.UserRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class UsersApplicationTests {

	@Autowired
	private UserCreateRestController createRestController;

	@Test
	void contextLoads() {
		User user = new User("userName", "firstName",
				"lastName", LocalDate.of(2020, 12, 05), "email");
		Assert.assertNull("User Id should be null", user.getId());
		createRestController.insert(user);
		Assert.assertNotNull("User Id should be not null", user.getId());
	}

	@Test
	void userList() {

	}


}
