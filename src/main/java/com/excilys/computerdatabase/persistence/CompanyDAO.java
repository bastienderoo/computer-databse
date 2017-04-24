package com.excilys.computerdatabase.persistence;

import java.util.List;

import com.excilys.computerdatabase.model.Company;

public interface CompanyDAO {
    /**
     * list company.
     * @param page10 page10
     * @return listcompany
     */
    List<Company> getList(int page10);
    /**
     * get company by id.
     * @param id id
     * @return company
     */
    Company getCompanyById(long id);

}
