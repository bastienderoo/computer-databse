package com.excilys.computerdatabase.service.implementation;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.persistence.implementation.CompanyDAOImp;
import com.excilys.computerdatabase.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImp implements CompanyService {
    @Autowired
    private CompanyDAOImp companyDAOImp;

    public List<Company> getList() {
        return companyDAOImp.getList();
    }


    public Company getCompanyById(long id) {


        return companyDAOImp.getCompanyById(id);
    }

    public Company getCompanyByName(String name) {
        return companyDAOImp.getCompanyByName(name);
    }


}
