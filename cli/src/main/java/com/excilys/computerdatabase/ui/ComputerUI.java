package com.excilys.computerdatabase.ui;

import java.util.Scanner;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.util.Strings;

import com.excilys.computerdatabase.model.ComputerDTO;
import com.excilys.computerdatabase.model.Page;

public class ComputerUI {

    private ComputerDTO computerDTO;
    private Page<ComputerDTO> pageComputer;

    public ComputerDTO findComputer() {

        Client client = ClientBuilder.newClient();
        WebTarget base = client.target("http://localhost:8080/webapp/final/");
        System.out.println("Find a computer");
        System.out.print("Enter a id : ");
        Scanner scan = new Scanner(System.in);
        long id;
        id = scan.nextLong();
        scan.nextLine();
        WebTarget find = base.path("computer/" + id);
        Invocation.Builder builder = find.request(MediaType.APPLICATION_JSON_TYPE);
        return builder.get(ComputerDTO.class);

    }

    public void add() {
        Client client = ClientBuilder.newClient();
        WebTarget base = client.target("http://localhost:8080/webapp/final/");
        computerDTO = new ComputerDTO();
        Scanner scan = new Scanner(System.in);
        String name = "";
        System.out.println("Add a computer");
        while (Strings.isBlank(name)) {
            System.out.print("Enter a name : (not null)");
            name = scan.nextLine();
        }
        computerDTO.setName(name);
        System.out.print("Enter an introduced date : ");
        computerDTO.setDateIntroduced(scan.nextLine());
        System.out.print("Enter a discontinued date : ");
        computerDTO.setDateDiscontinued(scan.nextLine());
        System.out.print("Enter an id of company : ");
        computerDTO.setIdCompany(scan.nextLong());
        scan.nextLine();

        WebTarget find = base.path("computer/");
        Invocation.Builder builder = find.request(MediaType.APPLICATION_JSON_TYPE);
        builder.post(Entity.entity(computerDTO, MediaType.APPLICATION_JSON_TYPE));
    }
}
