package com.excilys.computerdatabase.service.implementation;

import java.util.List;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.persistence.implementation.CompanyDAOImp;
import com.excilys.computerdatabase.service.CompanyService;

public class CompanyServiceImp implements CompanyService {

    private CompanyDAOImp companyDAO = new CompanyDAOImp();

    public List<Company> getList() {
        return companyDAO.getList();
    }


    public Company getCompanyById(long id) {
        return companyDAO.getCompanyById(id);
    }

    public Company getCompanyByName(String name) {
        return companyDAO.getCompanyByName(name);
    }

}
