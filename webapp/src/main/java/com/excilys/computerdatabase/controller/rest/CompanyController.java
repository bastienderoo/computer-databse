package com.excilys.computerdatabase.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Page;
import com.excilys.computerdatabase.service.CompanyService;

@RestController
@RequestMapping(value = "/final/company")
public class CompanyController {

    // ALL

    @Autowired
    private CompanyService companyService;

    public void setCompanyService(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping(value = "/")
    public Page<Company> getAllCompanies() {
        return companyService.getList();
    }

    @GetMapping(value = "/{id}")
    public Company getCompanyId(@PathVariable long id) {
        return companyService.getCompanyById(id);
    }

    @DeleteMapping(value = "/{id}")
    public Company delete(@PathVariable long id) {
        return companyService.delete(id);
    }

}
