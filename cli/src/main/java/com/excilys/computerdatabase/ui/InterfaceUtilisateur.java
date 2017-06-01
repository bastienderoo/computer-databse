package com.excilys.computerdatabase.ui;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.ComputerDTO;
import com.excilys.computerdatabase.model.Page;

public class InterfaceUtilisateur {

    public static void main(String... $n) {
        ComputerUI computerUI = new ComputerUI();
        CompanyUI companyUI = new CompanyUI();
        
        ComputerDTO computer = computerUI.findComputer();
        System.out.println(computer.toString());
        
        computerUI.add();
        
        Company company = companyUI.findCompany();
        System.out.println(company.toString());

        Page<Company> pageCompany = companyUI.findAllCompany();
        for (int i = 0; i < pageCompany.getList().size(); i++) {
            System.out.println(pageCompany.getList().get(i).toString());
        }
                
        
    }
}
