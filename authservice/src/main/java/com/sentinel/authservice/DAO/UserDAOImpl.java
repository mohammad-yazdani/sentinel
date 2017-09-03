package com.sentinel.authservice.DAO;


import com.sentinel.authservice.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Repository
@Transactional
public class UserDAOImpl implements UserDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public boolean save(User user) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.getTransaction();

        session.saveOrUpdate(user);
        transaction.commit();

        return true;
    }

    @Override
    public boolean delete(User user) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.getTransaction();

        session.delete(user);
        transaction.commit();

        return true;
    }

    @Override
    public User findByUsername(String username) {
        Session session = sessionFactory.getCurrentSession();

        return (User) session.createCriteria(User.class)
                .add(Restrictions.eq("username", username))
                .uniqueResult();
    }

    @Override
    public ArrayList<User> findByEmail(String email) {
        Session session = sessionFactory.getCurrentSession();

        return (ArrayList<User>) session.createCriteria(User.class)
                .add(Restrictions.eq("email", email))
                .list();
    }
}
