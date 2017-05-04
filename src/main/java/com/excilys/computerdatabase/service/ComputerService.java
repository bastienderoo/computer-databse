package com.excilys.computerdatabase.service;

import java.util.List;

import com.excilys.computerdatabase.model.Computer;

import com.excilys.computerdatabase.model.ComputerDTO;

public interface ComputerService {
    /**
     * delete a computer.
     *
     * @param id id
     */
    void delete(long id);

    /**
     * create a list of computers.
     *
     * @param page        page to show
     * @param nbrElements number of elements per page
     * @return liscomputer
     */
    List<ComputerDTO> getList(int page, int nbrElements);

    /**
     * add a computer.
     *
     * @param computer computer
     * @return long
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
    List<ComputerDTO> getComputerByName(String name);

    /**
     * get the number of computers.
     *
     * @return int
     */
    int getNumberComputer();

}
