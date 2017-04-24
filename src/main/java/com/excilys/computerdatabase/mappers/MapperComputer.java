package com.excilys.computerdatabase.mappers;

import com.excilys.computerdatabase.model.ComputerDTO;
import com.excilys.computerdatabase.service.CompanyServiceImp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;

public class MapperComputer {
    public static ComputerDTO mapperComputer(Computer computer) {

        String dateDiscontinued = computer.getDateDiscontinued().toString();
        String dateIntroduced = computer.getDateIntroduced().toString();
        String company = computer.getcompany().getName();
        Long idCompany = computer.getId();

        ComputerDTO computerDTO = new ComputerDTO.Builder(computer.getName()).id(computer.getId())
                .dateIntroduced(dateIntroduced).dateDiscontinued(dateDiscontinued).company(company).idCompany(idCompany)
                .build();
        return computerDTO;
    }

    public static Computer mapperComputerDTO(ComputerDTO computerDTO) {
        CompanyServiceImp companyService = new CompanyServiceImp();
        LocalDate dateDiscontinued = LocalDate.parse(computerDTO.getDateDiscontinued());
        LocalDate dateIntroduced = LocalDate.parse(computerDTO.getDateIntroduced());
        Company company = companyService.getCompanyById(computerDTO.getIdCompany());

        Computer computer = new Computer.Builder(computerDTO.getName()).id(computerDTO.getId())
                .dateIntroduced(dateIntroduced).dateDiscontinued(dateDiscontinued).company(company).build();
        return computer;
    }

    public static List<ComputerDTO> mapperComputer(List<Computer> computer) {
        List<ComputerDTO> listcomputerDTO = new ArrayList<ComputerDTO>();

        for (Computer c : computer) {
            String dateDiscontinued = c.getDateDiscontinued() != null ? c.getDateDiscontinued().toString() : "-";
            String dateIntroduced = c.getDateIntroduced() != null ? c.getDateIntroduced().toString() : "-";
            String company = c.getcompany() != null ? c.getcompany().getName() : "-";
            Long idCompany = c.getcompany() != null ? c.getcompany().getId() : 0L;
            System.out.println(company);
            ComputerDTO computerDTO = new ComputerDTO.Builder(c.getName()).id(c.getId()).dateIntroduced(dateIntroduced)
                    .dateDiscontinued(dateDiscontinued).company(company).idCompany(idCompany).build();
            listcomputerDTO.add(computerDTO);
        }

        return listcomputerDTO;
    }
}
