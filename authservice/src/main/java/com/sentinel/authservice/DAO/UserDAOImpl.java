package com.sentinel.authservice.DAO;


import com.sentinel.authservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.websocket.Session;
import java.util.ArrayList;

@Repository
@Transactional
public class UserDAOImpl implements UserDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public boolean save(User user) {
        Session session = sessionFactory.getCurrentSession();
    }

    @Override
    public boolean update(User user) {
    }

    @Override
    public boolean delete(User user) {
    }

    @Override
    public User findByUsername(String username) {
    }

    @Override
    public ArrayList<User> findByEmail(String email) {
        return null;
    }
}
