package com.excilys.computerdatabase.model;

import java.text.DateFormat;
import com.excilys.computerdatabase.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Computer {

	private String name;
	private Date dateIntroduced;
	private Date dateDiscontinued;

	public Computer(String name) {
		this.name = name;
	}

	public Computer(String name, String dateIntroduced, String dateDiscontinued) throws DateException {
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
			} else {
				throw new DateException("La date d'introduction doit être antérieure à la date d'arret");
			}

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		try {
			Computer c1 = new Computer("", "2000-13-2", "2001-1-12");
			AddComputer test = new AddComputer("zzzz","2000-11-21", "2000-1-12",12);
			ListComputer c = new ListComputer();
		
		} catch (DateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	}

}
