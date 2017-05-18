package com.excilys.computerdatabase.controller;

import com.excilys.computerdatabase.mappers.MapperComputer;
import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.model.ComputerDTO;
import com.excilys.computerdatabase.service.implementation.CompanyServiceImp;
import com.excilys.computerdatabase.service.implementation.ComputerServiceImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by excilys on 17/05/17.
 */
@Controller
@RequestMapping("/addComputer")
public class AddComputerController {
    @Autowired
    ComputerServiceImp computerServiceImp;
    @Autowired
    CompanyServiceImp companyServiceImp;

    private static final Logger LOGGER = LoggerFactory.getLogger(AddComputerController.class);

    @RequestMapping(method = RequestMethod.GET)
    public String get(ModelMap model) {
        List<Company> listCompany = companyServiceImp.getList();
        model.addAttribute("companyId", listCompany);
        model.addAttribute("computer", new ComputerDTO.Builder().build());
        return "addComputer";
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView post(ModelMap model,
            @RequestParam(value = "computer", defaultValue = "") @Valid final ComputerDTO computerDTO,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("redirect:/ErrorPage");
        }
        Computer computer = MapperComputer.mapperDTOIntoComputer(computerDTO);
        LOGGER.info("Computer Demande Add : " + computer);
        computerServiceImp.add(computer);
        return new ModelAndView("redirect:/Dashboard");
    }

}
