package com.excilys.computerdatabase.ui;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.service.CompanyServiceImp;
import com.excilys.computerdatabase.service.ComputerServiceImp;

public class CommandSetters {

    Scanner sc = new Scanner(System.in);
    private ComputerServiceImp computerService = new ComputerServiceImp();
    private CompanyServiceImp companyService = new CompanyServiceImp();
    private static final Logger LOGGER = Logger.getLogger(CommandSetters.class.getName());
/**
 * affiche la liste des ordinateurs.
 */
    public void listcomputer() {

        LOGGER.info("Entrer quelle page vous voulez afficher.");
        int page10 = sc.nextInt();
        List<Computer> listcomputer = computerService.getList(page10);
        LOGGER.info(listcomputer.toString());
    }
/**
 * ajout d'un ordinateur.
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
 * supression d'un ordinateur.
 */
    public void deleteComputer() {

        LOGGER.info("entrer l'id de l'ordinateur à supprimer");
        long idDelete = sc.nextLong();
        computerService.delete(idDelete);

    }
/**
 * Affiche la liste des entreprises.
 */
    public void listCompany() {
        LOGGER.info("Entrer quelle page vous voulez afficher.");
        int page10 = sc.nextInt();
        List<Company> listcompany = companyService.getList(page10);
        LOGGER.info(listcompany.toString());
    }
/**
 * Mise à jour d'un ordinateur.
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
