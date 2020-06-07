package com.springboot.pre_boot.service;

import com.springboot.pre_boot.model.Role;
import com.springboot.pre_boot.model.User;

import java.util.List;

public interface UserService {
    boolean addUser(User user, String[] role);
    List<User> getAllUser();
    void deleteUser(long id);
    void editUser(User user, String[] role);
    User getUserById(long id);
    List<Role> getAllRoles();
}
