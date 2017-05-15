package com.excilys.computerdatabase.servlets;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.excilys.computerdatabase.model.ComputerDTO;
import com.excilys.computerdatabase.service.ComputerService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * Servlet implementation class Dashboard
 */
@WebServlet("/Dashboard")
public class Dashboard extends HttpServlet {
    private static final long serialVersionUID = 1L;
    int page;
    int numberElements = 10;
    String search = "";
    //ComputerService computerService;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        try {
            page = Integer.parseInt(request.getParameter("page")) - 1;
        } catch (NumberFormatException e) {
            page = 0;
        }

        try {
            if (numberElements != Integer.parseInt(request.getParameter("numberElements"))) {
                numberElements = Integer.parseInt(request.getParameter("numberElements"));
            }
        } catch (NumberFormatException e) {

        }
        List<ComputerDTO> listComputer;
        WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(Dashboard.this.getServletContext());
        ComputerService computerService = (ComputerService) context.getBean("computerService");

        search = request.getParameter("search");
        int numberComputers;
        if (!Objects.equals(search, "") && search != null) {
            listComputer = computerService.getComputerByName(search);
            numberComputers = listComputer.size();
        } else {
            listComputer = computerService.getList(page, numberElements);
            numberComputers = computerService.getNumberComputer();


        }

        int numberPage = numberComputers / numberElements + 1;
        request.setAttribute("numberComputers", numberComputers);
        request.setAttribute("computerList", listComputer);
        request.setAttribute("page", page + 1);
        request.setAttribute("numberElements", numberElements);
        request.setAttribute("numberPage", numberPage);
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(request, response);

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(Dashboard.this.getServletContext());

       ComputerService computerService = (ComputerService) context.getBean("computerService");
        String listSelection = request.getParameter("selection");
        System.out.println(listSelection);
        String[] idString = listSelection.split(",");
        for (String idS : idString) {
            if (idS != null) {
                Long id = Long.parseLong(idS);
                computerService.delete(id);
            }


        }
        doGet(request, response);
    }

}
