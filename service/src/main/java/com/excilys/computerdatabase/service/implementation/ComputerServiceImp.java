package com.excilys.computerdatabase.service.implementation;

import java.util.List;

import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.computerdatabase.mappers.MapperComputer;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.model.ComputerDTO;
import com.excilys.computerdatabase.model.Page;
import com.excilys.computerdatabase.persistence.ComputerDAO;
import com.excilys.computerdatabase.service.ComputerService;
import com.excilys.computerdatabase.util.ServiceException;

@Transactional
@Service
public class ComputerServiceImp implements ComputerService {

    @Autowired
    private ComputerDAO computerDAO;

    private final Logger LOGGER = LoggerFactory.getLogger(ComputerServiceImp.class.getName());
    @Autowired
    MapperComputer mapperComputer;

    public ComputerDTO delete(long id) {
        return mapperComputer.mapperComputerIntoDTO(computerDAO.delete(id));
    }

    public Page<ComputerDTO> getList(int page, int nbrElements) {
        Page<Computer> pageComputer = computerDAO.getList(page, nbrElements);
        List<ComputerDTO> listComputerDTO = mapperComputer.mapperComputerIntoDTO(pageComputer.getList());
        return new Page<ComputerDTO>(listComputerDTO, page, nbrElements);
    }

    public long add(ComputerDTO computerDTO) {
        if (Strings.isNotEmpty(computerDTO.getDateIntroduced())) {
            computerDTO.setDateIntroduced(computerDTO.getDateIntroduced() + " 00:00:00.0");
        }
        if (Strings.isNotEmpty(computerDTO.getDateDiscontinued())) {
            computerDTO.setDateDiscontinued(computerDTO.getDateDiscontinued() + " 00:00:00.0");
        }
        Computer computer = mapperComputer.mapperDTOIntoComputer(computerDTO);
        if (computer.getDateIntroduced() != null && computer.getDateDiscontinued() != null) {
            if (computer.getDateIntroduced().isBefore(computer.getDateDiscontinued())) {
                long id = computerDAO.add(computer);
                return id;
            } else {
                LOGGER.info("Date introduced must be before Date dicontinued");
                throw new ServiceException();
            }
        } else {
            if (Strings.isNotEmpty(computer.getName())) {
                return computerDAO.add(computer);
            } else {
                LOGGER.info("Name must be filled");
                throw new ServiceException();
            }
        }
    }

    public ComputerDTO update(ComputerDTO computerDTO) {
        if (Strings.isNotEmpty(computerDTO.getDateIntroduced())) {
            computerDTO.setDateIntroduced(computerDTO.getDateIntroduced() + " 00:00:00.0");
        }
        if (Strings.isNotEmpty(computerDTO.getDateDiscontinued())) {
            computerDTO.setDateDiscontinued(computerDTO.getDateDiscontinued() + " 00:00:00.0");
        }
        Computer computer = mapperComputer.mapperDTOIntoComputer(computerDTO);
        if (computer.getDateIntroduced() != null && computer.getDateDiscontinued() != null) {
            if (computer.getDateIntroduced().isBefore(computer.getDateDiscontinued())) {
                return mapperComputer.mapperComputerIntoDTO(computerDAO.update(computer));
            } else {
                LOGGER.info("Date introduced must be before Date dicontinued");
                throw new ServiceException();
            }
        } else {
            if (Strings.isNotEmpty(computer.getName())) {
                return mapperComputer.mapperComputerIntoDTO(computerDAO.update(computer));
            } else {
                LOGGER.info("Name must be filled");
                throw new ServiceException();
            }
        }

    }

    public ComputerDTO getComputerById(long id) {

        return mapperComputer.mapperComputerIntoDTO(computerDAO.getComputerById(id));

    }

    public Page<ComputerDTO> getComputerByName(String name) {
        Page<Computer> listComputer = computerDAO.getComputerByName(name);
        return new Page<ComputerDTO>(mapperComputer.mapperComputerIntoDTO(listComputer.getList()));

    }

    public int getNumberComputer() {
        return computerDAO.numberComputer();
    }

}
