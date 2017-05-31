package com.excilys.computerdatabase.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.computerdatabase.mappers.MapperComputer;
import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.model.ComputerDTO;
import com.excilys.computerdatabase.service.CompanyService;
import com.excilys.computerdatabase.service.ComputerService;

/**
 * Created by excilys on 17/05/17.
 */
@Controller
@RequestMapping("/addComputer")
public class AddComputerController {
    @Autowired
    ComputerService computerServiceImp;
    @Autowired
    CompanyService companyServiceImp;
    @Autowired
    MapperComputer mapperComputer;

    private static final Logger LOGGER = LoggerFactory.getLogger(AddComputerController.class);

    @GetMapping
    public String get(ModelMap model) {
        List<Company> listCompany = companyServiceImp.getList().getList();
        model.addAttribute("companyId", listCompany);
        return "addComputer";
    }


    @PostMapping
    public ModelAndView post(ModelMap model, @Valid @ModelAttribute ComputerDTO computerDTO) {


        
        LOGGER.info("Computer Demande Add : " + computerDTO);
        computerServiceImp.add(computerDTO);
        return new ModelAndView("redirect:/Dashboard");
    }

}
