package com.excilys.computerdatabase.service.implementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.computerdatabase.model.User;
import com.excilys.computerdatabase.persistence.UserDAO;

@Service
public class UserServiceImp {

    @Autowired
    UserDAO userDAO;

    private final Logger LOGGER = LoggerFactory.getLogger(UserServiceImp.class.getName());

    public User add(User user) {
        LOGGER.info("UserService.add is running");
        return userDAO.add(user);
    }

    public User update(User user) {
        LOGGER.info("UserService.update is running");
        return userDAO.update(user);
    }

    public User delete(String name) {
        LOGGER.info("UserService.delete is running");
        return userDAO.delete(name);
    }

    public User findByName(String name) {
        LOGGER.info("UserService.findByName is running");
        return userDAO.findByName(name);
    }
}
