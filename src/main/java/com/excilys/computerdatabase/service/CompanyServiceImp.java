package com.excilys.computerdatabase.service;

import java.util.List;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.persistence.CompanyDAOImp;

public class CompanyServiceImp implements CompanyService {
	private CompanyDAOImp companyDAO = new CompanyDAOImp();

	public List<Company> getList(int page10) {
		List<Company> listcompany = companyDAO.getList(page10);
		return listcompany;
	}

	public Company getCompanyById(long id) {
		Company company = companyDAO.getCompanyById(id);
		return company;

	}
}
