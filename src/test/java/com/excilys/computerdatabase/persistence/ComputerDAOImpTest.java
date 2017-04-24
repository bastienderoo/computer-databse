package com.excilys.computerdatabase.persistence;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.excilys.computerdatabase.model.Company;

import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.persistence.implementation.ComputerDAOImp;
import com.excilys.computerdatabase.util.ComputerDatabaseDAOException;

public class ComputerDAOImpTest {

    protected ComputerDAOImp computerDAOImp;

    @Before
    public void setUp() {
        computerDAOImp = new ComputerDAOImp();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getByIdTest() {
        Computer computer = computerDAOImp.getComputerById(1l);
        assertTrue(computer.getId() == 1l);
        assertTrue(computer.getName().equals("MacBook Pro 15.4 inch"));
        assertTrue(computer.getDateIntroduced() == null);
        assertTrue(computer.getDateDiscontinued() == null);
        assertTrue(computer.getcompany().getId() == 1l);
    }

    @Test
    public void testAdd() {
        Computer computer = new Computer.Builder("testName").build();
        long idComputer = computerDAOImp.add(computer);
        Computer computer2 = computerDAOImp.getComputerById(idComputer);
        System.out.println(computer);
        System.out.println(computer2);
        assertTrue(computer.getName().equals(computer2.getName()));
        assertTrue(computer.getDateIntroduced() == computer2.getDateIntroduced());
        assertTrue(computer.getDateDiscontinued() == computer2.getDateDiscontinued());
        computerDAOImp.delete(idComputer);
    }

    @Test(expected = ComputerDatabaseDAOException.class)
    public void testDelete() {

        Computer computer = new Computer.Builder("testName").build();
        long idComputer = computerDAOImp.add(computer);
        computerDAOImp.delete(idComputer);
        computerDAOImp.getComputerById(idComputer);

    }

    @Test
    public void testUpdate() {
        Computer computer = new Computer.Builder("testName").build();
        long idComputer = computerDAOImp.add(computer);
        Computer computer2 = new Computer.Builder("testName2").id(idComputer).build();
        System.out.println(computer2.getId());
        computerDAOImp.update(computer2);
        Computer computer3 = computerDAOImp.getComputerById(idComputer);
        System.out.println(computer3.getName());
        assertTrue(computer3.getName().equals("testName2"));
        computerDAOImp.delete(idComputer);
    }

    @Test
	public void testList() {
		Company company1 = new Company.Builder("Apple Inc.").id(1l).build();
		Company company2 = new Company.Builder("Thinking Machines").id(2l).build();
		Computer computer1 = new Computer.Builder("MacBook Pro 15.4 inch").id(1l).company(company1).build();
		Computer computer2 = new Computer.Builder("CM-2a").id(2l).company(company2).build();
		Computer computer3 = new Computer.Builder("CM-200").id(3l).company(company2).build();
		Computer computer4 = new Computer.Builder("CM-5e").id(4l).company(company2).build();
		Computer computer5 = new Computer.Builder("CM-5").id(5l).company(company2).dateIntroduced(LocalDate.parse("1991-01-01")).build();
		Computer computer6 = new Computer.Builder("MacBook Pro").id(6l).company(company1).dateIntroduced(LocalDate.parse("2006-01-10")).build();
		Computer computer7 = new Computer.Builder("Apple IIe").id(7l).build();
		Computer computer8 = new Computer.Builder("Apple IIc").id(8l).build();
		Computer computer9 = new Computer.Builder("Apple IIGS").id(9l).build();
		Computer computer10 = new Computer.Builder("Apple IIc Plus").id(10l).build();
		List<Computer> listComputer = new ArrayList<Computer>(){{add(computer1);add(computer2);add(computer3);add(computer4);add(computer5);add(computer6);add(computer7);add(computer8);add(computer9);add(computer10);}};
		List<Computer> listComputer2 =computerDAOImp.getList(0);
		assertTrue(listComputer.equals(listComputer2));
	}

}
