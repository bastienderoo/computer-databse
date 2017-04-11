package com.excilys.computerdatabase.ui;

import java.util.Scanner;

import com.excilys.computerdatabase.service.CommandClient;

public class InterfaceUtilisateur {

	Scanner sc = new Scanner(System.in);
	private char connect;

	public InterfaceUtilisateur() {
		while (connect != 'O') {
			System.out.println("Voulez-vous connecter?");
			connect = sc.nextLine().charAt(0);
			switch (connect) {
			case 'O': CommandClient connection = new CommandClient();
				break;
			case 'N' :System.exit(0); 
				break;
			default : System.out.println("entrée incorrect");

			}
		}
		

	}

}
