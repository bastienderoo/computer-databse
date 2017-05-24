package com.excilys.computerdatabase.service;

import com.excilys.computerdatabase.model.Company;

import java.util.List;

public interface CompanyService {
    /**
     * create a list of companies.
     *
     * @return list of companies
     */
    List<Company> getList();

    /**
     * get a company by its ID.
     *
     * @param id
     *            id
     * @return company
     */
    Company getCompanyById(long id);

    /**
     * get a company by its name.
     * 
     * @param name
     *            name
     * @return company
     */
    Company getCompanyByName(String name);

    Company delete(long id);
}
