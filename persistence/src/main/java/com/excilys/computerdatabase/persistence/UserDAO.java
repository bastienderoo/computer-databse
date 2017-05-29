package com.excilys.computerdatabase.persistence;

import com.excilys.computerdatabase.model.User;

public interface UserDAO {

    User add(User user);

    User update(User user);

    User findByName(String name);

    User delete(String name);

}
