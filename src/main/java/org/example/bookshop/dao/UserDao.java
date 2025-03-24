package org.example.bookshop.dao;

import org.example.bookshop.security.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDao extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
}
