package com.excilys.computerdatabase.model;

import java.text.DateFormat;
import com.excilys.computerdatabase.service.CommandClient;
import com.excilys.computerdatabase.ui.InterfaceUtilisateur;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.*;

public class Computer {

	private String name;
	private Date dateIntroduced;
	private Date dateDiscontinued;
	private int iDcompany;


	public Computer(String name) {
		this.name = name;
	}

	public Computer(String name, String dateIntroduced, String dateDiscontinued,int iDcompany) throws DateException {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date d1 = null;
		Date d2 = null;
		try {
			d1 = new Date(df.parse(dateIntroduced).getTime());
			d2 = new Date(df.parse(dateDiscontinued).getTime());
			if (d2.after(d1)) {
				this.name = name;
				this.dateIntroduced = d1;
				this.dateDiscontinued = d2;
				this.iDcompany=iDcompany;
			} else {
				throw new DateException("La date d'introduction doit être antérieure à la date d'arret");
			}

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		

	


		InterfaceUtilisateur a = new InterfaceUtilisateur();
		
	}

}
