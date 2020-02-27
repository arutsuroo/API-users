package com.wipro.users;

import com.wipro.api.users.common.UsersDto;
import com.wipro.api.users.common.UsersMapper.UsersMapper;
import com.wipro.api.users.create.UsersCreateRestController;
import com.wipro.api.users.delete.UsersDeleteRestController;
import com.wipro.api.users.detail.UsersDetailRestController;
import com.wipro.api.users.update.UsersUpdateRestController;
import com.wipro.domain.role.Role;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.RollbackException;
import java.time.LocalDate;
import static org.assertj.core.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class UsersApplicationTests {

	@Autowired
	private UsersCreateRestController createRestController;

	@Autowired
	private UsersUpdateRestController updateRestController;

	@Autowired
	private UsersDetailRestController detailRestController;

	@Autowired
	private UsersDeleteRestController deleteRestController;


	@Test
	public void insertUser() {
		UsersDto user = new UsersDto("theDoctor", "John", "Smith", LocalDate.of(2020, 01, 12), "doctor@tardis.com", null);
		createRestController.insert(user);
		assertThat(user).isNotNull();
	}

	@Test
	public void updateUserEmail(){
		UsersDto user = new UsersDto("theDoctor", "John", "Smith", LocalDate.of(2020, 01, 12), "doctor@tardis.com", null);
		String email = user.getEmail();
		createRestController.insert(user);
		user.setEmail("rogerinho2012@gmail.com");
		updateRestController.update(1L, user);
		String updated_email = user.getEmail();
		assertThat(email).isNotEqualTo(updated_email);
	}

	@Ignore
	public void deleteUser(){
		UsersDto user = new UsersDto("theDoctor", "John", "Smith", LocalDate.of(2020, 01, 12), "doctor@tardis.com", null);
		createRestController.insert(user);

	}

//	@Test
//	void insertUserWithMissingValues() {
//		assertThatThrownBy(()->{UsersDto user = new UsersDto("theDoctor", "John", "Smith", LocalDate.of(2020, 01, 12), "doctor@tardis.com", null);
//			createRestController.insert(user);}).isInstanceOf(RollbackException.class).hasMessageContaining("Could not commit JPA transaction; nested exception is javax.persistence.RollbackException: Error while committing the transaction");
//	}


}
