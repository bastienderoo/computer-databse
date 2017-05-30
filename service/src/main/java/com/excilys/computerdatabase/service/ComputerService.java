package com.excilys.computerdatabase.service;

import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.model.ComputerDTO;
import com.excilys.computerdatabase.model.Page;

public interface ComputerService {
    /**
     * delete a computer.
     *
     * @param id id
     */
    Computer delete(long id);

    /**
     * create a list of computers.
     *
     * @param page        page to show
     * @param nbrElements number of elements per page
     * @return liscomputer
     */
    Page<ComputerDTO> getList(int page, int nbrElements);

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
    Computer update(Computer computer);

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
    Page<ComputerDTO> getComputerByName(String name);

    /**
     * get the number of computers.
     *
     * @return int
     */
    int getNumberComputer();


}
