package com.sentinel.authservice.DAO;


import com.sentinel.authservice.model.User;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import java.util.ArrayList;

public class UserDAOImpl extends HibernateDaoSupport implements UserDAO {
    @Override
    public boolean save(User user) {
        return (boolean) getHibernateTemplate().save(user);
    }

    @Override
    public boolean update(User user) {
        getHibernateTemplate().update(user);
        return true;
    }

    @Override
    public boolean delete(User user) {
        getHibernateTemplate().delete(user);
        return true;
    }

    @Override
    public User findByUsername(String username) {
        return null;
        //return getHibernateTemplate();
    }

    @Override
    public ArrayList<User> findByEmail(String email) {
        return null;
    }
}
