package com.excilys.computerdatabase.ui;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;

import com.excilys.computerdatabase.mappers.MapperComputer;
import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.model.ComputerDTO;
import com.excilys.computerdatabase.model.Page;
import com.excilys.computerdatabase.service.implementation.CompanyServiceImp;
import com.excilys.computerdatabase.service.implementation.ComputerServiceImp;

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
        LOGGER.info("Combien d'éléments pas page ?");
        int nbrelement = sc.nextInt();
        Page<ComputerDTO> listcomputer = computerService.getList(page10,nbrelement);
        LOGGER.info(listcomputer.getList().toString());
    }
    
    @Autowired
    MapperComputer mapperComputer;
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
        ComputerDTO computerDTO = mapperComputer.mapperComputerIntoDTO(computer);
        computerService.add(computerDTO);
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
        
       
        Page<Company> listcompany = companyService.getList();
        LOGGER.info(listcompany.getList().toString());
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
        ComputerDTO computerDTO = mapperComputer.mapperComputerIntoDTO(computer);
        computerService.update(computerDTO);
    }

}