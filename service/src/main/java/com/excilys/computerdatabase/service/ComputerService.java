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
    ComputerDTO delete(long id);

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
    long add(ComputerDTO computerDTO);

    /**
     * update a computer.
     *
     * @param computer computer
     */
    ComputerDTO update(ComputerDTO computerDTO);

    /**
     * get a computer by its ID.
     *
     * @param id id
     * @return computer
     */
    ComputerDTO getComputerById(long id);

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
