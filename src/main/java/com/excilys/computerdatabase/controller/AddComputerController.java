package com.excilys.computerdatabase.controller;

import com.excilys.computerdatabase.mappers.MapperComputer;
import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.model.ComputerDTO;
import com.excilys.computerdatabase.service.CompanyService;
import com.excilys.computerdatabase.service.ComputerService;
import com.excilys.computerdatabase.service.implementation.CompanyServiceImp;
import com.excilys.computerdatabase.service.implementation.ComputerServiceImp;

import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
    ComputerService computerServiceImp;
    @Autowired
    CompanyService companyServiceImp;

    private static final Logger LOGGER = LoggerFactory.getLogger(AddComputerController.class);

    @RequestMapping(method = RequestMethod.GET)
    public String get(ModelMap model) {
        List<Company> listCompany = companyServiceImp.getList();
        model.addAttribute("companyId", listCompany);
        return "addComputer";
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView post(ModelMap model, @RequestParam(value = "companyId", defaultValue = "") final long idCompany,
            @RequestParam(value = "computerName", defaultValue = "") final String computerName,
            @RequestParam(value = "introduced", defaultValue = "") final String introduced,
            @RequestParam(value = "discontinued", defaultValue = "") final String discontinued) {

        ComputerDTO computerDTO = new ComputerDTO.Builder().name(computerName)
                .dateIntroduced((Strings.isNotBlank(introduced)) ? introduced + " 00:00:00.0" : introduced)
                .dateDiscontinued((Strings.isNotBlank(discontinued)) ? discontinued + " 00:00:00.0" : discontinued)
                .idCompany(idCompany).build();
        Computer computer = MapperComputer.mapperDTOIntoComputer(computerDTO);
        LOGGER.info("Computer Demande Add : " + computer);
        computerServiceImp.add(computer);
        return new ModelAndView("redirect:/Dashboard");
    }

}
