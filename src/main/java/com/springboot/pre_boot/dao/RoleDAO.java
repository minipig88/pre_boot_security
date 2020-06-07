package com.springboot.pre_boot.dao;

import com.springboot.pre_boot.model.Role;

import java.util.List;

public interface RoleDAO {
    Role getRoleByName(String role);
    List<Role> getAllRoles();
}
