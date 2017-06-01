package com.excilys.computerdatabase.ui;

import java.util.Scanner;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Page;


public class CompanyUI {
    private Company company;
    private Page<Company> listCompany;

    public Company findCompany() {

        Client client = ClientBuilder.newClient();
        WebTarget base = client.target("http://localhost:8080/webapp/final/");
        System.out.println("Find a company");
        System.out.print("Enter a id : ");
        Scanner scan = new Scanner(System.in);
        long id;
        id = scan.nextLong();
        scan.nextLine();
        WebTarget find = base.path("company/" + id);
        Invocation.Builder builder = find.request(MediaType.APPLICATION_JSON_TYPE);
        company = builder.get(Company.class);
        
        return company;
    }

    public Page<Company> findAllCompany() {
        Client client = ClientBuilder.newClient();
        WebTarget base = client.target("http://localhost:8080/webapp/final/");
        WebTarget findall = base.path("company/");
        Invocation.Builder builder = findall.request(MediaType.APPLICATION_JSON_TYPE);
        listCompany = builder.get(new GenericType<Page<Company>>() {
        });
        
        return listCompany;

    }
}
