package com.springboot.pre_boot.dao;

import com.springboot.pre_boot.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User findByUsername(String username) {
        return entityManager.createQuery("FROM User WHERE username=:paramUserName", User.class).
                setParameter("paramUserName", username).
                getResultList().
                stream().
                findFirst().
                orElse(null);
    }

    @Override
    public void add(User user) {
        entityManager.persist(user);
    }

    @Override
    public User findById(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public List<User> getAll() {
        return entityManager.createQuery("FROM User", User.class).getResultList();
    }

    @Override
    public User find(User user) {
        return entityManager.find(User.class, user.getId());
    }

    @Override
    public void delete(User user) {
        entityManager.remove(find(user));
    }

    @Override
    public void edit(User user) {
        entityManager.merge(user);
    }
}
