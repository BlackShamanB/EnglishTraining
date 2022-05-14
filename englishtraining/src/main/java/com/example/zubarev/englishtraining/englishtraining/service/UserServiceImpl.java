package com.example.zubarev.englishtraining.englishtraining.service;

import java.util.Optional;

import com.example.zubarev.englishtraining.englishtraining.model.User;
import com.example.zubarev.englishtraining.englishtraining.repos.UserRepos;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepos userRepos;

    public UserServiceImpl(UserRepos userRepos) {
        this.userRepos = userRepos;
    }

    @Override
    public User addUser(User user) {
        return userRepos.save(user);
    }

    @Override
    public void deleteUser(User user) {
        userRepos.delete(user);
    }

    @Override
    public User changeUser(User user) {
        return userRepos.save(user);
    }

    @Override
    public Iterable<User> getAll() {
        return userRepos.findAll();
    }

    @Override
    public Optional<User> getUser(Long id) {
        return userRepos.findById(id);
    }

    @Override
    public User getName(String name) {
        return userRepos.findUserByName(name);
    }

    @Override
    public User getUser(User searchUser) {
        User user = userRepos.findUserByName(searchUser.getName());
        if (searchUser.getRole() == user.getRole())
            return user;
        else
            return null;
    }

    @Override
    public void deleteUserId(Long id) {
        userRepos.deleteById(id);
    }

    @Override
    public Long count() {
        return userRepos.count();
    }
}
