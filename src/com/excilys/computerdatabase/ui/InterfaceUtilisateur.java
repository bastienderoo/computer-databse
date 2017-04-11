package com.excilys.computerdatabase.ui;

import java.util.Scanner;

import com.excilys.computerdatabase.service.CommandClient;

public class InterfaceUtilisateur {

	Scanner sc = new Scanner(System.in);
	private char connect;
	CommandClient connection;

	public InterfaceUtilisateur() {
		while (connect != 'O') {
			System.out.println("Voulez-vous connecter?");
			connect = sc.nextLine().charAt(0);
			switch (connect) {
			case 'O': {connection = new CommandClient();connection.Connection();}
				break;
			case 'N' :System.exit(0); 
				break;
			default : System.out.println("entrée incorrect");

			}
		}
		
		System.out.println("Que voulez-vous faire 1(add) 2(delete) 3(liste computer) 4(liste company) 5(update) 6(disconnect)");
		int choix = sc.nextInt();
		switch(choix){
		case 1 :connection.addComputer();
		case 2: connection.deleteComputer();
		case 3: connection.listcomputer();
		case 4: connection.listCompany();
		case 5:connection.updateCompany();
		case 6:connection.CloseConnection();
		
			
		}

	}

}
