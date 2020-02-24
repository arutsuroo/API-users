package com.wipro.users;

import com.wipro.api.users.create.UsersCreateRestController;
import com.wipro.domain.users.User;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class UsersApplicationTests {

	@Autowired
	private UsersCreateRestController createRestController;

//	@Test
//	void contextLoads() {
//		User user = new User("userName", "firstName",
//				"lastName", LocalDate.of(2020, 12, 05), "email");
//		Assert.assertNull("User Id should be null", user.getId());
//		createRestController.insert(user);
//		Assert.assertNotNull("User Id should be not null", user.getId());
//	}

	@Test
	void userList() {

	}


}
