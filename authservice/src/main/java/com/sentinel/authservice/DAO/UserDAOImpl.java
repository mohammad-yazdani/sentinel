package com.sentinel.authservice.DAO;

import com.sentinel.authservice.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;

@Transactional
public class UserDAOImpl implements UserDAO {

    private static Logger log = LoggerFactory.getLogger(UserDAOImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    public void delete(User user) {
        entityManager.remove(user);
    }

    @Override
    public User findByUsername(String username) {
        return entityManager.find(User.class, username);
    }
    @Override
    public ArrayList<User> findByEmail(String email) {
        return null;
    }
}
