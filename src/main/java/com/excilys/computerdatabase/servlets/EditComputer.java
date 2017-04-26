package com.excilys.computerdatabase.servlets;

import com.excilys.computerdatabase.mappers.MapperComputer;
import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.model.ComputerDTO;
import com.excilys.computerdatabase.service.CompanyService;
import com.excilys.computerdatabase.service.CompanyServiceImp;
import com.excilys.computerdatabase.service.ComputerService;
import com.excilys.computerdatabase.service.ComputerServiceImp;

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
@WebServlet("/EditComputer")
public class EditComputer extends HttpServlet {



    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        CompanyService companyService = new CompanyServiceImp();
        ComputerService computerService = new ComputerServiceImp();
        List<Company> listCompany = companyService.getList();
        String computerName = request.getParameter("computerName");
        if (computerName != "") {
            String introduced = request.getParameter("introduced");
            String discontinued = request.getParameter("discontinued");
            Long companyId = Long.parseLong(request.getParameter("companyId"));
            System.out.println(introduced);
            ComputerDTO computerDTO = new ComputerDTO.Builder(computerName).dateIntroduced(introduced)
                    .dateDiscontinued(discontinued).idCompany(companyId).build();
            Computer computer = MapperComputer.mapperComputerDTO(computerDTO);
            computerService.update(computer);

        }
        request.setAttribute("companyId", listCompany);
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/editComputer.jsp").forward(request, response);

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }
}
