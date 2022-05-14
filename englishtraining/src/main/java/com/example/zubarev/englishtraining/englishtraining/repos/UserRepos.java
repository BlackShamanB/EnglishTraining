package com.example.zubarev.englishtraining.englishtraining.repos;

import com.example.zubarev.englishtraining.englishtraining.model.User;

import org.springframework.data.repository.CrudRepository;

public interface UserRepos extends CrudRepository<User,Long> {
    User findUserByName(String name);
}
