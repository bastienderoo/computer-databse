package com.excilys.computerdatabase.persistence;

import java.util.List;

import com.excilys.computerdatabase.model.Computer;

public interface ComputerDAO {
	public void delete(long id);
	public List<Computer> getList(int page10);
	public long add(Computer computer);
	public void update(Computer computer);
	public Computer getComputerById(long id);
	public Computer getComputerByName(String name);
	
}
