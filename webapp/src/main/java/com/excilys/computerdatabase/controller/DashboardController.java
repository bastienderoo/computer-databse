package com.excilys.computerdatabase.controller;

import java.util.List;
import java.util.Locale;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.computerdatabase.config.SecurityConfig;
import com.excilys.computerdatabase.model.ComputerDTO;
import com.excilys.computerdatabase.service.ComputerService;

/**
 * Created by excilys on 17/05/17.
 */
@Controller
@RequestMapping("/Dashboard")
public class DashboardController {
    private static final Logger LOGGER = LoggerFactory.getLogger(DashboardController.class);
    int numberElements = 10;
    int page = 1;
    @Autowired
    private ComputerService computerServiceImp;
    @Autowired
    PasswordEncoder pwdEnc;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView get(ModelMap model,
            @RequestParam(value = "numberElements", required = false) Integer numberElements,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "search", defaultValue = "") final String search,
            @RequestParam(value = "mylocale", defaultValue = "en") String language, Locale locale) {
        
        
        if (numberElements != null) {
            this.numberElements = numberElements;
            this.page = 0;

        } else {
            if (page != null) {
                this.page = page - 1;
            }
        }


        List<ComputerDTO> listComputer;

        int numberComputers;
        if (!Objects.equals(search, "") && search != null) {
            listComputer = computerServiceImp.getComputerByName(search).getList();
            numberComputers = listComputer.size();
        } else {
            listComputer = computerServiceImp.getList(this.page, this.numberElements).getList();
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

    @PostMapping("/delete")
    public ModelAndView post(@RequestParam(value = "selection", defaultValue = "") final String listSelection) {
        String[] idString = listSelection.split(",");
        for (String idS : idString) {
            if (idS != null) {
                Long id = Long.parseLong(idS);

                computerServiceImp.delete(id);
            }

        }

        return new ModelAndView("redirect:/Dashboard");

    }

    @PostMapping("/logout")
    public ModelAndView logoutPage(HttpServletRequest request, HttpServletResponse response,
            @RequestParam(value = "logout", defaultValue = "") final String logout) {
        if (Strings.isNotBlank(logout)) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth != null) {
                new SecurityContextLogoutHandler().logout(request, response, auth);
            }
            return new ModelAndView("redirect:/login?logout");

        } else
            return new ModelAndView("dashboard");
    }

}
