package com.excilys.computerdatabase.service.implementation;

import com.excilys.computerdatabase.mappers.MapperComputer;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.model.ComputerDTO;
import com.excilys.computerdatabase.persistence.implementation.ComputerDAOImp;
import com.excilys.computerdatabase.service.ComputerService;
import com.excilys.computerdatabase.util.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComputerServiceImp implements ComputerService {
    @Autowired
    private ComputerDAOImp computerDAOImp;


    private final Logger LOGGER = LoggerFactory.getLogger(ComputerServiceImp.class.getName());


    public Computer delete(long id) {
        return computerDAOImp.delete(id);
    }

    public List<ComputerDTO> getList(int page, int nbrElements) {
        List<Computer> listComputer = computerDAOImp.getList(page, nbrElements);
        return MapperComputer.mapperComputerIntoDTO(listComputer);
    }

    public long add(Computer computer) {
        if (computer.getDateIntroduced() != null && computer.getDateDiscontinued() != null) {
            if (computer.getDateIntroduced().isBefore(computer.getDateDiscontinued())) {
                long id = computerDAOImp.add(computer);
                return id;
            } else {
                LOGGER.info("Date introduced must be before Date dicontinued");
                throw new ServiceException();
            }
        } else {
            return computerDAOImp.add(computer);
        }
    }

    public Computer update(Computer computer) {
        if (computer.getDateIntroduced() != null && computer.getDateDiscontinued() != null) {
            if (computer.getDateIntroduced().isBefore(computer.getDateDiscontinued())) {
                return computerDAOImp.update(computer);
            } else {
                LOGGER.info("Date introduced must be before Date dicontinued");
                throw new ServiceException();
            }
        } else {
            return computerDAOImp.update(computer);
        }

    }

    public Computer getComputerById(long id) {
        return computerDAOImp.getComputerById(id);

    }

    public List<ComputerDTO> getComputerByName(String name) {
        List<Computer> listComputer = computerDAOImp.getComputerByName(name);
        return MapperComputer.mapperComputerIntoDTO(listComputer);

    }

    public int getNumberComputer() {
        return computerDAOImp.numberComputer();
    }


}
