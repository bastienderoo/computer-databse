package com.excilys.computerdatabase.persistence;

import java.util.List;

import com.excilys.computerdatabase.model.Computer;

public interface ComputerDAO {

    /**
     * delete a computer.
     *
     * @param id id
     */
    void delete(long id);

    /**
     * Create a list of computers.
     *
     * @param page        page to show
     * @param nbrElements number of elements per page
     * @return List of Computers
     */

    List<Computer> getList(int page, int nbrElements);

    /**
     * add a computer.
     *
     * @param computer computer
     * @return id
     */
    long add(Computer computer);

    /**
     * update a computer.
     *
     * @param computer computer
     */
    void update(Computer computer);

    /**
     * get a computer by its ID.
     *
     * @param id id
     * @return computer
     */
    Computer getComputerById(long id);

    /**
     * get a computer by its name.
     *
     * @param name name
     * @return computer
     */
    List<Computer> getComputerByName(String name);

}
