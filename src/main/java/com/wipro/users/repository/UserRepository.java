package com.wipro.users.repository;

import com.wipro.users.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface UserRepository  extends JpaRepository<User, Long> {
}

