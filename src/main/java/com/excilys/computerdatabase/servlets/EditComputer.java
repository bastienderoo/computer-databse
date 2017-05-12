package com.excilys.computerdatabase.servlets;

import com.excilys.computerdatabase.mappers.MapperComputer;
import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.model.ComputerDTO;
import com.excilys.computerdatabase.service.CompanyService;
import com.excilys.computerdatabase.service.implementation.CompanyServiceImp;
import com.excilys.computerdatabase.service.ComputerService;
import com.excilys.computerdatabase.service.implementation.ComputerServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by excilys on 26/04/17.
 */
@WebServlet("/editComputer")
public class EditComputer extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        CompanyService companyService = new CompanyServiceImp();
        ComputerService computerService = new ComputerServiceImp();
        List<Company> listCompany = companyService.getList();
        String idString = request.getParameter("id");
        if (idString != null) {
            long id = Long.parseLong(idString);
            System.out.println(id);
            Computer computer = computerService.getComputerById(id);
            System.out.println(computer.getName());
            request.setAttribute("computerName", computer.getName());
            request.setAttribute("introduced", computer.getDateIntroduced());
            request.setAttribute("discontinued", computer.getDateDiscontinued());
        }
        request.setAttribute("computerId", idString);
        request.setAttribute("companyId", listCompany);
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/editComputer.jsp").forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ComputerService computerService = new ComputerServiceImp();


        String idCompanyString = request.getParameter("companyId");
        String idString = request.getParameter("id");
        if (idString != null) {
            String computerName = request.getParameter("computerName");
            long id = Long.parseLong(idString);

            if (computerName != null) {
                String introduced = request.getParameter("introduced");
                String discontinued = request.getParameter("discontinued");
                String companyIdString = request.getParameter("companyId");
                if (companyIdString != null && !companyIdString.equals("")) {
                    long idCompany = Long.parseLong(idCompanyString);

                    ComputerDTO computerDTO = new ComputerDTO.Builder(computerName)
                            .id(id)
                            .dateIntroduced(introduced)
                            .dateDiscontinued(discontinued)
                            .idCompany(idCompany).build();
                    Computer computer = MapperComputer.mapperDTOIntoComputer(computerDTO);
                    computerService.update(computer);
                }
            }
        }


        response.sendRedirect("Dashboard");
    }
}
