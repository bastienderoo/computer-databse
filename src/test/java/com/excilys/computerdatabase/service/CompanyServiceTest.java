//package com.excilys.computerdatabase.service;
//
//import static org.junit.Assert.assertTrue;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//import com.excilys.computerdatabase.model.Company;
//import com.excilys.computerdatabase.persistence.implementation.CompanyDAOImp;
//
//public class CompanyServiceTest {
//    protected CompanyServiceImp companyService;
//
//    @Before
//    public void setUp() {
//        companyService = new CompanyServiceImp();
//    }
//
//    @After
//    public void tearDown() {
//    }
//
//    @Test
//    public void getByIdTest() {
//        Company company = companyService.getCompanyById(1l);
//        assertTrue(company.getId() == 1l);
//        assertTrue(company.getName().equals("Apple Inc."));
//    }
//
//    @Test
//    public void getList(){
//        Company company1 = new Company.Builder("Apple Inc.").id(1l).build();
//        Company company2 = new Company.Builder("Thinking Machines").id(2l).build();
//        Company company3 = new Company.Builder("RCA").id(3l).build();
//        Company company4 = new Company.Builder("Netronics").id(4l).build();
//        Company company5 = new Company.Builder("Tandy Corporation").id(5l).build();
//        Company company6 = new Company.Builder("Commodore International").id(6l).build();
//        Company company7 = new Company.Builder("MOS Technology").id(7l).build();
//        Company company8 = new Company.Builder("Micro Instrumentation and Telemetry Systems").id(8l).build();
//        Company company9 = new Company.Builder("IMS Associates, Inc.").id(9l).build();
//        Company company10 = new Company.Builder("Digital Equipment Corporation").id(10l).build();
//        List<Company> listCompany = new ArrayList<Company>(){{add(company1);add(company2);add(company3);add(company4);add(company5);add(company6);add(company7);add(company8);add(company9);add(company10);}};
//        List<Company> listCompany2 =companyService.getList(0);
//        System.out.println(listCompany);
//        System.out.println(listCompany2);
//        assertTrue(listCompany.equals(listCompany2));
//    }
//
//}
