package com.excilys.computerdatabase.persistence.implementation;

import java.util.function.Supplier;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.computerdatabase.model.QUser;
import com.excilys.computerdatabase.model.User;
import com.excilys.computerdatabase.persistence.UserDAO;
import com.querydsl.jpa.hibernate.HibernateQueryFactory;

@Repository
@Transactional
public class UserDAOImp implements UserDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDAOImp.class.getName());

    QUser qUser = QUser.user;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private SessionFactory sessionFactory;

    private Supplier<HibernateQueryFactory> queryFactory = () -> new HibernateQueryFactory(
            sessionFactory.getCurrentSession());

    @Override
    public User add(User user) {
        LOGGER.info("User.add is running");
        sessionFactory.getCurrentSession().save(user);
        return user;
    }

    @Override
    public User update(User user) {
        LOGGER.info("User.update is running");
        queryFactory.get().update(qUser).where(qUser.username.eq(user.getUsername()))
                .set(qUser.username, user.getUsername())
                .set(qUser.password, user.getPassword())
                .set(qUser.userRoles, user.getUserRoles())
                .set(qUser.enabled, user.isEnabled())
                .execute();
        return user;
    }
    
    @Override
    public User findByName(String name) {
        LOGGER.info("UserDAO.findByName is running");
        User user = queryFactory.get()
                .select(qUser)
                .from(qUser)
                .where(qUser.username.like(name))
                .fetchOne();
        
        return user;
    }
    
    @Override
    public User delete(String name){
        LOGGER.info("User.delete is running");
        User user = findByName(name);
       queryFactory.get().delete(qUser).where(qUser.username.eq(name)).execute();
        return user;
        
    }
}
