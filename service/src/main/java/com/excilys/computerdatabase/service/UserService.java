package com.excilys.computerdatabase.service;

import com.excilys.computerdatabase.model.User;

public interface UserService {
    User add(User user);

    User update(User user);

    User findByName(String name);

    User delete(String name);
}
