package com.example.springsecuritydemo.repository;

import com.example.springsecuritydemo.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUserName(String username);
}
