package com.excilys.computerdatabase.service;

import java.util.List;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.persistence.CompanyDAOImp;

public class CompanyServiceImp implements CompanyService {

    private CompanyDAOImp companyDAO = new CompanyDAOImp();
/**
 * .
 * @param page10 page10
 * @return listcompany
 */
    public List<Company> getList(int page10) {
        List<Company> listcompany = companyDAO.getList(page10);
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

}
