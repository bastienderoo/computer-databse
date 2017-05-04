package com.excilys.computerdatabase.service;

import java.util.List;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.persistence.implementation.CompanyDAOImp;

public class CompanyServiceImp implements CompanyService {

    private CompanyDAOImp companyDAO = new CompanyDAOImp();

    public List<Company> getList() {
        List<Company> listcompany = companyDAO.getList();
        return listcompany;
    }


    public Company getCompanyById(long id) {
        Company company = companyDAO.getCompanyById(id);
        return company;

    }

    public Company getCompanyByName(String name) {
        Company company = companyDAO.getCompanyByName(name);
        return company;

    }

}
