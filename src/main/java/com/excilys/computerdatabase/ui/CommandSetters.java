package com.excilys.computerdatabase.ui;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.model.ComputerDTO;
import com.excilys.computerdatabase.service.implementation.CompanyServiceImp;
import com.excilys.computerdatabase.service.implementation.ComputerServiceImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommandSetters {

    Scanner sc = new Scanner(System.in);
    private ComputerServiceImp computerService = new ComputerServiceImp();
    private CompanyServiceImp companyService = new CompanyServiceImp();
    private static final Logger LOGGER = LoggerFactory.getLogger(CommandSetters.class.getName());

    /**
     * show computers list.
     */
    public void listComputer() {

        LOGGER.info("Entrer quelle page vous voulez afficher.");
        int page10 = sc.nextInt();
        int elementPage = sc.nextInt();
        List<ComputerDTO> listcomputer = computerService.getList(page10, elementPage);
        LOGGER.info(listcomputer.toString());
    }

    /**
     * add a computer.
     */
    public void addComputer() {

        LOGGER.info("entrer le nom de l'ordinateur");
        String name = sc.nextLine();
        LOGGER.info("entrer la date d'introduction");
        String dateIntroducedString = sc.nextLine();
        LOGGER.info("entrer la date d'arrêt");
        String dateDiscontinuedString = sc.nextLine();
        LOGGER.info("entrer l'id de l'entreprise");
        long iDCompany = sc.nextLong();
        LocalDate dateIntroduced = LocalDate.parse(dateIntroducedString);
        LocalDate dateDiscontinued = LocalDate.parse(dateDiscontinuedString);
        Company company = companyService.getCompanyById(iDCompany);
        Computer computer = new Computer.Builder(name).dateIntroduced(dateIntroduced).dateDiscontinued(dateDiscontinued)
                .company(company).build();
        computerService.add(computer);
    }

    /**
     * delete a computer.
     */
    public void deleteComputer() {

        LOGGER.info("entrer l'id de l'ordinateur à supprimer");
        long idDelete = sc.nextLong();
        computerService.delete(idDelete);

    }

    /**
     * show companies list.
     */
    public void listCompany() {

        List<Company> listcompany = companyService.getList();
        LOGGER.info(listcompany.toString());
    }

    /**
     * update a computer.
     */
    public void updateComputer() {
        LOGGER.info("entrer l'id de l'ordinateur");
        long id = sc.nextLong();
        sc.nextLine();
        LOGGER.info("entrer le nom de l'ordinateur");
        String name = sc.nextLine();
        LOGGER.info("entrer la date d'introduction");
        String dateIntroducedString = sc.nextLine();
        LOGGER.info("entrer la date d'arrêt");
        String dateDiscontinuedString = sc.nextLine();
        LOGGER.info("entrer l'id de l'entreprise");
        long iDCompany = sc.nextLong();
        LocalDate dateIntroduced = LocalDate.parse(dateIntroducedString);
        LocalDate dateDiscontinued = LocalDate.parse(dateDiscontinuedString);
        Company company = companyService.getCompanyById(iDCompany);
        Computer computer = new Computer.Builder(name).id(id).dateIntroduced(dateIntroduced)
                .dateDiscontinued(dateDiscontinued).company(company).build();
        computerService.update(computer);
    }

}
