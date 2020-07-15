package com.exalt.sparepartsmanagement.service;


import com.exalt.sparepartsmanagement.dto.RoleDTO;
import com.exalt.sparepartsmanagement.model.Role;

import java.util.List;

public interface RoleService {

    List <RoleDTO> getAll(int page , int pageSize);

    RoleDTO get(String name);

    void save(RoleDTO roleDTO);

    void update(String name, RoleDTO roleDTO);

    void delete(String name);
}
