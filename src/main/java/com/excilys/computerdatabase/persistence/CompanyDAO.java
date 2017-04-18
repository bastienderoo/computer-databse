package com.excilys.computerdatabase.persistence;

import java.util.List;

import com.excilys.computerdatabase.model.Company;

public interface CompanyDAO {
	public List<Company> getList(int page10);
	public Company getCompanyById(long id);
	
}
