package com.excilys.computerdatabase.service;

import java.util.List;

import com.excilys.computerdatabase.model.Computer;

public interface ComputerService {
	public void delete(long id);
	public List<Computer> getList(int page10);
	public void add(Computer computer);
	public void update(Computer computer);
	public Computer getComputerById(long id);
	public Computer getComputerByName(String name);
}
