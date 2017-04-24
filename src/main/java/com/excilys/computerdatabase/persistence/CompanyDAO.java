package com.excilys.computerdatabase.persistence;

import java.util.List;

import com.excilys.computerdatabase.model.Company;

public interface CompanyDAO {
<<<<<<< HEAD
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

=======
	public List<Company> getList(int page10);
	public Company getCompanyById(long id);
	
>>>>>>> 73e0817a3d35b4bced2c74c09de8d96d764a7303
}
