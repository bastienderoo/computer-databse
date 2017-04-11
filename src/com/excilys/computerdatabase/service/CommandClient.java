package com.excilys.computerdatabase.service;

import java.sql.Connection;
import java.util.Scanner;

import com.excilys.computerdatabase.persistence.CompanyDAO;
import com.excilys.computerdatabase.persistence.ComputerDAO;
import com.excilys.computerdatabase.persistence.Database;

public class CommandClient {
	private Connection db = null;

	Scanner sc = new Scanner(System.in);
	private ComputerDAO a = null;
	private CompanyDAO b = null;

	public CommandClient() {

	}

	public Connection Connection() {
		System.out.println("entrer l'id");
		String user = sc.nextLine();
		System.out.println("entrer le mot de passe");
		String pwd = sc.nextLine();
		db = Database.getInstance(user, pwd);
		a = new ComputerDAO(db);
		b = new CompanyDAO(db);
		return db;

	}

	public Connection CloseConnection() {
		db = Database.closeConnection();
		return db;
	}

	public void listcomputer() {

		String[][] listcomp = a.listComputer();
		for (int i = 0; i < listcomp[0].length; i++) {
			for (int j = 0; j < listcomp.length; j++) {
				System.out.println(listcomp[j][i]);
			}
		}

	}

	public void addComputer() {

		System.out.println("entrer le nom de l'ordinateur");
		String name = sc.nextLine();
		System.out.println("entrer la date d'introduction");
		String dateIntroduced = sc.nextLine();
		System.out.println("entrer la date d'arrêt");
		String dateDiscontinued = sc.nextLine();
		System.out.println("entrer l'id de l'entreprise");
		int iDCompany = sc.nextInt();

		a.addComputer(name, dateIntroduced, dateDiscontinued, iDCompany);
	}

	public void deleteComputer() {

		System.out.println("entrer l'id de l'ordinate");
		int idDelete = sc.nextInt();
		a.deleteComputer(idDelete);

	}

	public void listCompany() {
		String[][] listcomp = b.listCompany();
		for (int i = 0; i < listcomp[0].length; i++) {
			for (int j = 0; j < listcomp.length; j++) {
				System.out.println(listcomp[j][i]);
			}

		}
	}

	public void updateCompany() {
		System.out.println("entrer l'id de l'ordinate");
		int idDelete = sc.nextInt();
		sc.nextLine();
		System.out.println("entrer le nom de l'ordinateur");
		String name = sc.nextLine();
		System.out.println("entrer la date d'introduction");
		String dateIntroduced = sc.nextLine();
		System.out.println("entrer la date d'arrêt");
		String dateDiscontinued = sc.nextLine();
		System.out.println("entrer l'id de l'entreprise");
		int iDCompany = sc.nextInt();
		
		a.updateComputer(idDelete, name, dateIntroduced, dateDiscontinued, iDCompany);
	}
}
