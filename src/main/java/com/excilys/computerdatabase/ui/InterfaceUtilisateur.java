package com.excilys.computerdatabase.ui;

import java.util.Scanner;
import java.util.logging.Logger;

public class InterfaceUtilisateur {

<<<<<<< HEAD
    Scanner sc = new Scanner(System.in);

    CommandSetters command = new CommandSetters();
    private static final Logger LOGGER = Logger.getLogger(CommandSetters.class.getName());
        /**
     * liste des commandes utilisateurs.
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
            command.listcomputer();
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
=======
	Scanner sc = new Scanner(System.in);

	CommandSetters command= new CommandSetters();
	private static final Logger LOGGER = Logger.getLogger(CommandSetters.class.getName());

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
			command.listcomputer();
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
>>>>>>> 73e0817a3d35b4bced2c74c09de8d96d764a7303

}
