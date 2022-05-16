package com.example.zubarev.englishtraining.englishtraining.repos;

import com.example.zubarev.englishtraining.englishtraining.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
