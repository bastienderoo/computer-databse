package com.excilys.computerdatabase.service;

import java.util.List;

import com.excilys.computerdatabase.model.Company;

public interface CompanyService {
	public List<Company> getList(int page10);
	public Company getCompanyById(long id);
}
