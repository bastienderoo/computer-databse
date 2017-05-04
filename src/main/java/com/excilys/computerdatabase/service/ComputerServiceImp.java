package com.excilys.computerdatabase.service;

import java.util.List;
import java.util.logging.Logger;

import com.excilys.computerdatabase.mappers.MapperComputer;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.model.ComputerDTO;
import com.excilys.computerdatabase.persistence.implementation.ComputerDAOImp;
import com.excilys.computerdatabase.util.ServiceException;

public class ComputerServiceImp implements ComputerService {
    private ComputerDAOImp computerDAO = new ComputerDAOImp();
    private final Logger LOGGER = Logger.getLogger(ComputerServiceImp.class.getName());


    public void delete(long id) {
        computerDAO.delete(id);
    }

    public List<ComputerDTO> getList(int page, int nbrElements) {
        List<Computer> listComputer = computerDAO.getList(page, nbrElements);
        return MapperComputer.mapperComputerIntoDTO(listComputer);
    }

    public long add(Computer computer) {
        if (computer.getDateIntroduced() != null && computer.getDateDiscontinued() != null) {
            if (computer.getDateIntroduced().isBefore(computer.getDateDiscontinued())) {
                long id = computerDAO.add(computer);
                return id;
            } else {
                LOGGER.info("Date introduced must be before Date dicontinued");
                throw new ServiceException();
            }
        } else {
            long id = computerDAO.add(computer);
            return id;
        }
    }

    public void update(Computer computer) {
        if (computer.getDateIntroduced() != null && computer.getDateDiscontinued() != null) {
            if (computer.getDateIntroduced().isBefore(computer.getDateDiscontinued())) {
                computerDAO.update(computer);
            } else {
                LOGGER.info("Date introduced must be before Date dicontinued");
                throw new ServiceException();
            }
        } else {
            computerDAO.update(computer);
        }

    }

    public Computer getComputerById(long id) {
        Computer computer = computerDAO.getComputerById(id);
        return computer;
    }

    public List<ComputerDTO> getComputerByName(String name) {
        List<Computer> listComputer = computerDAO.getComputerByName(name);
        return MapperComputer.mapperComputerIntoDTO(listComputer);

    }

    public int getNumberComputer() {
        return computerDAO.nombreComputer();
    }

}
