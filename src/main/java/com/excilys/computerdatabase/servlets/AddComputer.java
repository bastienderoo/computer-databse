package com.excilys.computerdatabase.servlets;

import com.excilys.computerdatabase.mappers.MapperComputer;
import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.model.ComputerDTO;
import com.excilys.computerdatabase.service.CompanyService;
import com.excilys.computerdatabase.service.ComputerService;
import com.excilys.computerdatabase.service.implementation.CompanyServiceImp;
import com.excilys.computerdatabase.service.implementation.ComputerServiceImp;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * Servlet implementation class addComputer
 */
@WebServlet("/addComputer")
public class AddComputer extends HttpServlet {
    private static final long serialVersionUID = 1L;
    ApplicationContext context =
            new ClassPathXmlApplicationContext(new String[]{"services.xml"});

    public void setComputerService(ComputerServiceImp computerService) {
        this.computerService = computerService;
    }

    public void setCompanyService(CompanyServiceImp companyService) {
        this.companyService = companyService;
    }

    ComputerServiceImp computerService = (ComputerServiceImp) context.getBean("computerService");
    CompanyService companyService = (CompanyService) context.getBean("companyService");

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        List<Company> listCompany = companyService.getList();
        request.setAttribute("companyId", listCompany);
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/addComputer.jsp").forward(request, response);

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {




        String computerName = request.getParameter("computerName");
        if (!Objects.equals(computerName, "")) {
            String introduced = request.getParameter("introduced");
            String discontinued = request.getParameter("discontinued");
            String companyIdString = request.getParameter("companyId");
            if (companyIdString != null) {
                Long companyId = Long.parseLong(companyIdString);

                ComputerDTO computerDTO = new ComputerDTO.Builder(computerName).dateIntroduced(introduced)
                        .dateDiscontinued(discontinued).idCompany(companyId).build();
                Computer computer = MapperComputer.mapperDTOIntoComputer(computerDTO);
                computerService.add(computer);

            }
        }

        response.sendRedirect("Dashboard");
    }

}
