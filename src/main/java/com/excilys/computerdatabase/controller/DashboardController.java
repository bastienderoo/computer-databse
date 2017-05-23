package com.excilys.computerdatabase.controller;

import com.excilys.computerdatabase.model.ComputerDTO;
import com.excilys.computerdatabase.service.ComputerService;
import com.excilys.computerdatabase.service.implementation.ComputerServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Locale;
import java.util.Objects;

/**
 * Created by excilys on 17/05/17.
 */
@Controller
@RequestMapping("/Dashboard")
public class DashboardController {

    int numberElements = 10;
    int page = 1;
    @Autowired
    private ComputerService computerServiceImp;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView get(ModelMap model, @RequestParam(value = "numberElements", required = false) Integer numberElements,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "search", defaultValue = "") final String search,
            @RequestParam(value = "mylocale", defaultValue = "en") String language,
            Locale locale) {
        if (numberElements != null) {
            this.numberElements = numberElements;
            this.page = 0;
            
        } else {
            if (page!=null) {
                this.page = page-1;
            }
        }

        
        List<ComputerDTO> listComputer;

        int numberComputers;
        if (!Objects.equals(search, "") && search != null) {
            listComputer = computerServiceImp.getComputerByName(search);
            numberComputers = listComputer.size();
        } else {
            listComputer = computerServiceImp.getList(this.page, this.numberElements);
            numberComputers = computerServiceImp.getNumberComputer();
        }

        int numberPage = numberComputers / this.numberElements + 1;
        model.addAttribute("numberComputers", numberComputers);
        model.addAttribute("computerList", listComputer);
        model.addAttribute("page", this.page + 1);
        model.addAttribute("numberElements", this.numberElements);
        model.addAttribute("numberPage", numberPage);

        return new ModelAndView("dashboard");

    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView post(
            @RequestParam(value = "selection", defaultValue = "") final String listSelection) {
        String[] idString = listSelection.split(",");
        for (String idS : idString) {
            if (idS != null) {
                Long id = Long.parseLong(idS);

                computerServiceImp.delete(id);
            }

        }

        return new ModelAndView("redirect:/Dashboard");

    }

}
