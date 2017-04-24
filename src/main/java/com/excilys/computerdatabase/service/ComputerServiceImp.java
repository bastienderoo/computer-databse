package com.excilys.computerdatabase.service;

import java.util.List;

<<<<<<< HEAD
import com.excilys.computerdatabase.mappers.MapperComputer;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.model.ComputerDTO;
=======
import com.excilys.computerdatabase.model.Computer;
>>>>>>> 73e0817a3d35b4bced2c74c09de8d96d764a7303
import com.excilys.computerdatabase.persistence.ComputerDAOImp;
import com.excilys.computerdatabase.util.ComputerDatabaseDAOException;

public class ComputerServiceImp implements ComputerService {

<<<<<<< HEAD
    private ComputerDAOImp computerDAO = new ComputerDAOImp();
    /**
     * .
     * @param id id
     */
    public void delete(long id) {
        computerDAO.delete(id);
    }
    /**
     * .
     * @param page10 page10
     * @return listcomputer
     */
    public List<ComputerDTO> getList(int page10) {
        List<Computer> listcomputer = computerDAO.getList(page10);
        return MapperComputer.mapperComputer(listcomputer);
    }
    /**
     * .
     * @param computer computer
     * @return long
     */
    public long add(Computer computer) {
        if (computer.getDateIntroduced().isBefore(computer.getDateDiscontinued())) {
            long id = computerDAO.add(computer);
            return id;
        } else {
            throw new ComputerDatabaseDAOException("Date introduced must be before Date dicontinued");
        }
    }
    /**
     * .
     * @param computer computer
     */
    public void update(Computer computer) {
        if (computer.getDateIntroduced().isBefore(computer.getDateDiscontinued())) {
            computerDAO.update(computer);
        } else {
            throw new ComputerDatabaseDAOException("Date introduced must be before Date dicontinued");
        }
    }
    /**
     * .
     * @param id id
     * @return computer
     */
    public Computer getComputerById(long id) {
        Computer computer = computerDAO.getComputerById(id);
        return computer;
    }
    /**
     * .
     * @param name name
     * @return computer
     */
    public Computer getComputerByName(String name) {
        Computer computer = computerDAO.getComputerByName(name);
        return computer;
    }
=======
	private ComputerDAOImp computerDAO = new ComputerDAOImp();

	public void delete(long id) {
		computerDAO.delete(id);
	}

	public List<Computer> getList(int page10) {
		List<Computer> listcomputer = computerDAO.getList(page10);
		return listcomputer;
	}

	public void add(Computer computer) {
		if (computer.getDateIntroduced().isBefore(computer.getDateDiscontinued())) {
			computerDAO.add(computer);
		} else
			throw new ComputerDatabaseDAOException("Date introduced must be before Date dicontinued");
	}

	public void update(Computer computer) {
		if (computer.getDateIntroduced().isBefore(computer.getDateDiscontinued())) {
			computerDAO.update(computer);
		} else
			throw new ComputerDatabaseDAOException("Date introduced must be before Date dicontinued");
	}

	public Computer getComputerById(long id) {
		Computer computer = computerDAO.getComputerById(id);
		return computer;
	}

	public Computer getComputerByName(String name) {
		Computer computer = computerDAO.getComputerByName(name);
		return computer;
	}
>>>>>>> 73e0817a3d35b4bced2c74c09de8d96d764a7303
}
