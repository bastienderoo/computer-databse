package com.excilys.computerdatabase.service;

import java.util.List;

import com.excilys.computerdatabase.model.Company;

public interface CompanyService {
    /**
     * .
     * 
     * @param page10
     *            page10
     * @return listcompany
     */
    List<Company> getList(int page10);

    /**
     * .
     * 
     * @param id
     *            id
     * @return company
     */
    Company getCompanyById(long id);

}
