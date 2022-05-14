package com.example.zubarev.englishtraining.englishtraining.service;

import java.util.Optional;

import com.example.zubarev.englishtraining.englishtraining.model.User;

public interface UserService {
    public User addUser(User user);
    public void deleteUser(User user);
    public User changeUser(User user);
    public Iterable<User> getAll();
    public Optional<User> getUser(Long id);
    public User getName(String name);
    public User getUser(User user);
    public void deleteUserId(Long id);
    public Long count();
}
