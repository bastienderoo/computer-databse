package com.excilys.computerdatabase.ui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class InterfaceUtilisateur {

    Scanner sc = new Scanner(System.in);

    CommandSetters command = new CommandSetters();
    private static final Logger LOGGER = LoggerFactory.getLogger(CommandSetters.class.getName());

    /**
     * list command user.
     */
    public InterfaceUtilisateur() {

        LOGGER.info(
                "Que voulez-vous faire 1(add) 2(delete) 3(liste computer) 4(liste company) 5(update) 6(disconnect)");
        int choix = sc.nextInt();
        switch (choix) {
            case 1:
                command.addComputer();
                break;
            case 2:
                command.deleteComputer();
                break;
            case 3:
                command.listComputer();
                break;
            case 4:
                command.listCompany();
                break;
            case 5:
                command.updateComputer();
                break;
            default:
                break;

        }
        LOGGER.info("programme terminé");

    }


}
