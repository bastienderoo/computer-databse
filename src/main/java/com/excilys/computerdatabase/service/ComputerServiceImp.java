package com.excilys.computerdatabase.service;

import java.util.List;

import com.excilys.computerdatabase.mappers.MapperComputer;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.model.ComputerDTO;
import com.excilys.computerdatabase.persistence.implementation.ComputerDAOImp;

public class ComputerServiceImp implements ComputerService {
    private ComputerDAOImp computerDAO = new ComputerDAOImp();

    /**
     * .
     *
     * @param id id
     */
    public void delete(long id) {
        computerDAO.delete(id);
    }

    /**
     * .
     *
     * @param page10 page10
     * @return listcomputer
     */
    public List<ComputerDTO> getList(int page, int nbrElements) {
        List<Computer> listcomputer = computerDAO.getList(page, nbrElements);
        return MapperComputer.mapperComputer(listcomputer);
    }

    /**
     * .
     *
     * @param computer computer
     * @return long
     */
    public long add(Computer computer) {
        if (computer.getDateIntroduced() != null && computer.getDateDiscontinued() != null) {
            if (computer.getDateIntroduced().isBefore(computer.getDateDiscontinued())) {
                long id = computerDAO.add(computer);
                return id;
            } else {
                throw new ServiceException("Date introduced must be before Date dicontinued");
            }
        } else {
            long id = computerDAO.add(computer);
            return id;
        }
    }

    /**
     * .
     *
     * @param computer computer
     */
    public void update(Computer computer) {
        if (computer.getDateIntroduced() != null && computer.getDateDiscontinued() != null) {
            if (computer.getDateIntroduced().isBefore(computer.getDateDiscontinued())) {
                computerDAO.update(computer);
            } else {
                throw new ServiceException("Date introduced must be before Date dicontinued");
            }
        } else {
            computerDAO.update(computer);
        }

    }

    /**
     * .
     *
     * @param id id
     * @return computer
     */
    public Computer getComputerById(long id) {
        Computer computer = computerDAO.getComputerById(id);
        return computer;
    }

    /**
     * .
     *
     * @param name name
     * @return computer
     */
    public List<ComputerDTO> getComputerByName(String name) {
        List<Computer> listComputer = computerDAO.getComputerByName(name);
        return MapperComputer.mapperComputer(listComputer);

    }

    public int getNombreComputer() {
        return computerDAO.nombreComputer();
    }

}
