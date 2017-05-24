package com.excilys.computerdatabase.controller;

import com.excilys.computerdatabase.mappers.MapperComputer;
import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.model.ComputerDTO;
import com.excilys.computerdatabase.persistence.implementation.CompanyDAOImp;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by excilys on 17/05/17.
 */
@Controller
@RequestMapping("/editComputer")
public class EditComputerController {
    @Autowired
    ComputerService computerServiceImp;
    @Autowired
    CompanyService companyServiceImp;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyDAOImp.class.getName());

    @GetMapping()
    public String get(ModelMap model, @RequestParam(value = "id", defaultValue = "") final String idString) {
        List<Company> listCompany = companyServiceImp.getList();

        if (idString != null) {
            long id = Long.parseLong(idString);

            Computer computer = computerServiceImp.getComputerById(id);

            model.addAttribute("computerName", computer.getName());
            model.addAttribute("introduced", computer.getDateIntroduced());
            model.addAttribute("discontinued", computer.getDateDiscontinued());
        }
        model.addAttribute("computerId", idString);
        model.addAttribute("companyId", listCompany);
        return "editComputer";
    }

    @PostMapping()
    public ModelAndView post(
            ModelMap model,
            @RequestParam(value = "companyId", defaultValue = "") final long idCompany,
            @RequestParam(value = "id", defaultValue = "") final long id,
            @RequestParam(value = "computerName", defaultValue = "") final String computerName,
            @RequestParam(value = "introduced", defaultValue = "") final String introduced,
            @RequestParam(value = "discontinued", defaultValue = "") final String discontinued


            ) {
        
        ComputerDTO computerDTO = new ComputerDTO.Builder()
                .name(computerName)
                .id(id)
                .dateIntroduced((Strings.isNotBlank(introduced))?introduced+" 00:00:00.0":introduced)
                .dateDiscontinued((Strings.isNotBlank(discontinued))?discontinued+" 00:00:00.0":discontinued)
                .idCompany(idCompany).build();
        Computer computer = MapperComputer.mapperDTOIntoComputer(computerDTO);
        computerServiceImp.update(computer);
        return new ModelAndView("redirect:/Dashboard");
    }


}

