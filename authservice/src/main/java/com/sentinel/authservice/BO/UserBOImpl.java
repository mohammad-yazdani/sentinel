package com.sentinel.authservice.BO;

import com.sentinel.authservice.DAO.UserDAO;
import com.sentinel.authservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

public class UserBOImpl implements UserBO {

    @Autowired
    UserDAO userDAO;

    @Override
    public boolean save(User user) {
        return userDAO.save(user);
    }

    @Override
    public boolean update(User user) {
        return userDAO.update(user);
    }

    @Override
    public boolean delete(User user) {
        return userDAO.delete(user);
    }

    @Override
    public User findByUsername(String username) {
        return userDAO.findByUsername(username);
    }

    @Override
    public ArrayList<User> findByEmail(String email) {
        return userDAO.findByEmail(email);
    }
}
