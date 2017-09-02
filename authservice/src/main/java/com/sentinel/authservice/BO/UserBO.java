package com.sentinel.authservice.BO;

import com.sentinel.authservice.model.User;

import java.util.ArrayList;

public interface UserBO {

    boolean save (User user);

    boolean update(User user);

    boolean delete(User user);

    User findByUsername (String username);

    ArrayList<User> findByEmail (String email);

}
