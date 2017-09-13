package com.sentinel.authservice.DAO;

import com.sentinel.authservice.model.User;

import java.util.ArrayList;

public interface UserDAO {

    void save (User user);

    void delete(User user);

    User findByUsername (String username);

    ArrayList<User> findByEmail (String email);
}
