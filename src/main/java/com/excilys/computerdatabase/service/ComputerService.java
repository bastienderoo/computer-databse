package com.excilys.computerdatabase.service;

import java.util.List;

import com.excilys.computerdatabase.model.Computer;

import com.excilys.computerdatabase.model.ComputerDTO;

public interface ComputerService {
    /**
     * .
     * @param id id
     */
    void delete(long id);
/**
 * .
 * @param page10 page10
 * @return liscomputer
 */
    List<ComputerDTO> getList(int page,int nbrElements);
/**
 * .
 * @param computer computer
 * @return long
 */
    long add(Computer computer);
/**
 * .
 * @param computer computer
 */
    void update(Computer computer);
/**
 * .
 * @param id id
 * @return computer
 */
    Computer getComputerById(long id);
/**
 * .
 * @param name name
 * @return computer
 */
    List<ComputerDTO> getComputerByName(String name);
    
    public int getNombreComputer();

}
