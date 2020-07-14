package com.exalt.sparepartsmanagement.service;


import com.exalt.sparepartsmanagement.model.Role;

import java.util.List;

public interface RoleService {

    List <Role> getAll(int page , int pageSize);

    Role get(String name);

    void save(Role role);

    void update(String name, Role role);

    void delete(String name);
}
