package com.springboot.pre_boot.dao;

import com.springboot.pre_boot.model.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoleDAOImpl implements RoleDAO {

    @PersistenceContext(unitName = "entityManagerFactory")
    private EntityManager entityManager;


    @Override
    public Role getRoleByName(String role) {
        return entityManager.createQuery("FROM Role WHERE name=:paramName", Role.class).
                setParameter("paramName", role).
                getSingleResult();
    }

    @Override
    public List<Role> getAllRoles() {
        return entityManager.createQuery("FROM Role", Role.class).getResultList();
    }
}
