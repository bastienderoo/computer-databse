package com.excilys.computerdatabase.persistence;

import java.util.List;

import com.excilys.computerdatabase.model.Computer;

public interface ComputerDAO {
<<<<<<< HEAD
    /**
     * .
     * @param id id
     */
    void delete(long id);
    /**
     * .
     * @param page10 page10
     * @return listcomputer
     */
    List<Computer> getList(int page10);
    /**
     * .
     * @param computer computer
     * @return id
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
    Computer getComputerByName(String name);

=======
	public void delete(long id);
	public List<Computer> getList(int page10);
	public long add(Computer computer);
	public void update(Computer computer);
	public Computer getComputerById(long id);
	public Computer getComputerByName(String name);
	
>>>>>>> 73e0817a3d35b4bced2c74c09de8d96d764a7303
}
