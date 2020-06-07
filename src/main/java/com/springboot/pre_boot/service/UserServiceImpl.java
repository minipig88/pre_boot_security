package com.springboot.pre_boot.service;

import com.springboot.pre_boot.dao.RoleDAO;
import com.springboot.pre_boot.dao.UserDAO;
import com.springboot.pre_boot.model.Role;
import com.springboot.pre_boot.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;
    private final RoleDAO roleDAO;
    private final PasswordEncoder passwordEncoder;

    private UserServiceImpl(UserDAO userDAO, RoleDAO roleDAO, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.roleDAO = roleDAO;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean addUser(User user, String[] role) {
        User checkUserFromDB = userDAO.findByUsername(user.getUsername());
        if (checkUserFromDB != null) {
            return false;
        }
        userDAO.add(userSetRolesAndEncodePassword(user, role));
        return true;
    }

    @Override
    public List<User> getAllUser() {
        return userDAO.getAll();
    }

    @Override
    public void deleteUser(long id) {
        User user = userDAO.findById(id);
        userDAO.delete(user);
    }

    @Override
    public void editUser(User user, String[] role) {
        userDAO.edit(userSetRolesAndEncodePassword(user, role));
    }

    @Override
    public User getUserById(long id) {
        return userDAO.findById(id);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleDAO.getAllRoles();
    }

    private User userSetRolesAndEncodePassword(User user, String[] role) {
        Set<Role> roles = new HashSet<>();
        for (String r : role) {
            roles.add(roleDAO.getRoleByName(r));
        }
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return user;
    }
}
