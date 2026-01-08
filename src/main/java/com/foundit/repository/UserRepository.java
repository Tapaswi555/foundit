package com.foundit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.foundit.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
