package com.springboot.pre_boot.dao;

import com.springboot.pre_boot.model.User;

import java.util.List;

public interface UserDAO {
    User findByUsername (String username);
    User findById(long id);
    void add(User user);
    List<User> getAll();
    void delete(User user);
    void edit(User user);
    User find(User user);
}
