package com.excilys.computerdatabase.service.implementation;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.persistence.CompanyDAO;
import com.excilys.computerdatabase.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImp implements CompanyService {
    @Autowired
    private CompanyDAO companyDAO;

    public List<Company> getList() {
        return companyDAO.getList();
    }

    public Company getCompanyById(long id) {

        return companyDAO.getCompanyById(id);
    }

    public Company getCompanyByName(String name) {
        return companyDAO.getCompanyByName(name);
    }

    
    public Company delete(long id) {
        return companyDAO.delete(id);
    }

}
