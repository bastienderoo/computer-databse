package com.excilys.computerdatabase.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.excilys.computerdatabase.model.ComputerDTO;
import com.excilys.computerdatabase.model.Page;
import com.excilys.computerdatabase.service.ComputerService;

@RestController
@RequestMapping(value = "/final/computer")
public class ComputerController {

    @Autowired
    ComputerService computerService;

    @GetMapping(value = "/")
    @ResponseBody
    public Page<ComputerDTO> getAllComuter(@RequestParam int page, @RequestParam int elements) {
        return computerService.getList(page, elements);
    }

    @PostMapping(value = "/")
    @ResponseBody
    public long add(@RequestBody ComputerDTO computerDTO) {
        return computerService.add(computerDTO);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseBody
    public ComputerDTO delete(@PathVariable long id) {
        return computerService.delete(id);
    }

    @PutMapping(value = "/")
    @ResponseBody
    public ComputerDTO update(@RequestBody ComputerDTO computerDTO) {
        return computerService.update(computerDTO);
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public ComputerDTO getComputerById(@PathVariable long id) {
        return computerService.getComputerById(id);
    }

}
