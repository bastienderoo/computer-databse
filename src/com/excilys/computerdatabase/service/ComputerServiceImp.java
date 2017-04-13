package com.excilys.computerdatabase.service;

import java.util.List;

import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.persistence.ComputerDAOImp;
import com.excilys.computerdatabase.util.ComputerDatabaseDAOException;

public class ComputerServiceImp implements ComputerService {

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
}
