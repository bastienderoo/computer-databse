package com.excilys.computerdatabase.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.computerdatabase.model.ComputerDTO;
import com.excilys.computerdatabase.service.ComputerService;
import com.excilys.computerdatabase.service.ComputerServiceImp;

/**
 * Servlet implementation class Dashboard
 */
@WebServlet("/Dashboard")
public class Dashboard extends HttpServlet {
    private static final long serialVersionUID = 1L;
    int page;
    int nombreElements = 10;
    String search = "";

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
            if (nombreElements != Integer.parseInt(request.getParameter("nombreElements"))) {
                nombreElements = Integer.parseInt(request.getParameter("nombreElements"));
            }
        } catch (NumberFormatException e) {

        }
        List<ComputerDTO> listComputer;

        ComputerService computerService = new ComputerServiceImp();
        search = request.getParameter("search");
        int nombreComputers;
        if (!Objects.equals(search, "") && search != null) {
            listComputer = computerService.getComputerByName(search);
            nombreComputers = listComputer.size();
        } else {
            listComputer = computerService.getList(page, nombreElements);
            nombreComputers = computerService.getNombreComputer();
        }

        int nombrePage = nombreComputers / nombreElements + 1;
        request.setAttribute("nombreComputers", nombreComputers);
        request.setAttribute("computerList", listComputer);
        request.setAttribute("page", page + 1);
        request.setAttribute("nombreElements", nombreElements);
        request.setAttribute("nombrePage", nombrePage);
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(request, response);

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        ComputerService computerService = new ComputerServiceImp();
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
