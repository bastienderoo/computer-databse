package com.excilys.computerdatabase.persistence;

import static org.junit.Assert.assertFalse;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.excilys.computerdatabase.model.User;
import com.excilys.computerdatabase.model.UserRole;
import com.excilys.computerdatabase.persistence.implementation.UserDAOImp;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={com.excilys.computerdatabase.config.SpringWebConfig.class,com.excilys.computerdatabase.config.Hibernate.class,com.excilys.computerdatabase.config.SpringRootConfig.class})
public class UserDAOImpTest {
    @Autowired
    private UserDAO userDAO;

    @Test
    public void addTest() {
        User user = new User("UserTest", "PasswordTest", UserRole.ROLE_ADMIN, true);
        userDAO.add(user);
        User userTest = userDAO.findByName("UserTest");
        assertTrue(user.equals(userTest));
    }

    @Test
    public void deleteTest() {
        User user = userDAO.findByName("JL");
        userDAO.delete(user.getUsername());
        User user2 = userDAO.findByName("JL");
        assertFalse(user.equals(user2));
    }
}
