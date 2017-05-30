
package com.excilys.computerdatabase.persistence;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.persistence.implementation.ComputerDAOImp;

public class ComputerDAOImpTest {

    protected ComputerDAOImp computerDAOImp;

    @Before
    public void setUp() {
        computerDAOImp = new ComputerDAOImp();
    }

    @After
    public void tearDown() {
    }

//    @Test
//    public void getByIdTest() {
//        Computer computer = computerDAOImp.getComputerById(1l);
//        assertTrue(computer.getId() == 1l);
//        assertTrue(computer.getName().equals("MacBook Pro 15.4 inch"));
//        assertTrue(computer.getDateIntroduced() == null);
//        assertTrue(computer.getDateDiscontinued() == null);
//        assertTrue(computer.getcompany().getId() == 1l);
//    }
//
//    @Test
//    public void testAdd() {
//        Computer computer = new Computer.Builder("testName").build();
//        long idComputer = computerDAOImp.add(computer);
//        Computer computer2 = computerDAOImp.getComputerById(idComputer);
//        System.out.println(computer);
//        System.out.println(computer2);
//        assertTrue(computer.getName().equals(computer2.getName()));
//        assertTrue(computer.getDateIntroduced() == computer2.getDateIntroduced());
//        assertTrue(computer.getDateDiscontinued() == computer2.getDateDiscontinued());
//        computerDAOImp.delete(idComputer);
//    }
//
//    @Test(expected = ComputerDatabaseDAOException.class)
//    public void testDelete() {
//
//        Computer computer = new Computer.Builder("testName").build();
//        long idComputer = computerDAOImp.add(computer);
//        computerDAOImp.delete(idComputer);
//        computerDAOImp.getComputerById(idComputer);
//
//    }
//
//    @Test
//    public void testUpdate() {
//        Computer computer = new Computer.Builder("testName").build();
//        long idComputer = computerDAOImp.add(computer);
//        Computer computer2 = new Computer.Builder("testName2").id(idComputer).build();
//        System.out.println(computer2.getId());
//        computerDAOImp.update(computer2);
//        Computer computer3 = computerDAOImp.getComputerById(idComputer);
//        System.out.println(computer3.getName());
//        assertTrue(computer3.getName().equals("testName2"));
//        computerDAOImp.delete(idComputer);
//    }

}
