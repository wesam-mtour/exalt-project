package com.exalt.sparepartsmanagement.controller;


import com.exalt.sparepartsmanagement.dto.RoleDTO;
import com.exalt.sparepartsmanagement.model.Role;
import com.exalt.sparepartsmanagement.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class
    /*
    erfererger
     */

RoleController {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private RoleService roleService;

    @GetMapping(value = "/api/v1/roles", params = {"page","pageSize"})
    public List<RoleDTO> getRoles(@RequestParam("page") int page, @RequestParam("pageSize") int pageSize) {
        logger.info("Role controller method -getRoles");
        return roleService.getAll(page,pageSize);
    }

    @GetMapping(value = "/api/v1/roles/{name}")
    public RoleDTO getSpecificRole(@PathVariable String name) {
        logger.info("Role controller method -getSpecificRole");
        return roleService.get(name);
    }

    @PostMapping(value = "/api/v1/roles")
    public String createNewRole(@Valid @RequestBody RoleDTO roleDTO) {
        logger.info("Role controller method -createNewRole");
        roleService.save(roleDTO);
        return "role added successfully";
    }

    @PutMapping(value = "/api/v1/roles/{name}")
    public String updateRole(@PathVariable String name, @RequestBody RoleDTO roleDTO) {
        logger.info("Role controller method -updateRole");
        roleService.update(name, roleDTO);
        return "Role updated successfully ";
    }

    @DeleteMapping(value = "/api/v1/roles/{name}")
    public String deleteRole(@PathVariable String name) {
        logger.info("Role controller method -deleteRole");
        roleService.delete(name);
        return "Role deleted successfully";
    }

}
