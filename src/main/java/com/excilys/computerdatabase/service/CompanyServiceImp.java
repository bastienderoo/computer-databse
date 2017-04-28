package com.excilys.computerdatabase.service;

import java.util.List;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.persistence.implementation.CompanyDAOImp;

public class CompanyServiceImp implements CompanyService {

    private CompanyDAOImp companyDAO = new CompanyDAOImp();
/**
 * .
 * @param page10 page10
 * @return listcompany
 */
    public List<Company> getList() {
        List<Company> listcompany = companyDAO.getList();
        return listcompany;
    }
/**
 * .
 * @param id id
 * @return company
 */
    public Company getCompanyById(long id) {
        Company company = companyDAO.getCompanyById(id);
        return company;

    }
    
    public Company getCompanyByName(String name) {
        Company company = companyDAO.getCompanyByName(name);
        return company;

    }

}
