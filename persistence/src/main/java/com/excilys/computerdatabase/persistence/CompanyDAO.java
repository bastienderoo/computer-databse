package com.excilys.computerdatabase.persistence;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Page;


public interface CompanyDAO {

    /**
     * list company.
     *
     * @return listcompany
     */
    Page<Company> getList();

    /**
     * get company by id.
     *
     * @param id id
     * @return company
     */
    Company getCompanyById(long id);

    /**
     * get a company by its name.
     *
     * @param name name
     * @return company
     */
    Company getCompanyByName(String name);

    
    Company delete(long id);

}
