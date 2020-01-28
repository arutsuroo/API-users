package com.wipro.domain.users;

import com.wipro.domain.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface UserRepository  extends JpaRepository<User, Long> {
}

