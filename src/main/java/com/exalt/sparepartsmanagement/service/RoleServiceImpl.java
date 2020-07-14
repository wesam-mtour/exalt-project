package com.exalt.sparepartsmanagement.service;


import com.exalt.sparepartsmanagement.repository.RoleRepository;
import com.exalt.sparepartsmanagement.error.ConflictExceptions;
import com.exalt.sparepartsmanagement.error.NotFoundExceptions;
import com.exalt.sparepartsmanagement.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public List<Role> getAll(int page , int pageSize)
    { if (page < 1){
        throw  new NotFoundExceptions("Invalid page number");
    }
        if (pageSize < 1){
            throw  new NotFoundExceptions("Invalid page size ");
        }
        /**
         *
         */
        Pageable paging = PageRequest.of(page-1, pageSize);
        Page<Role> pagedResult = roleRepository.findAll(paging);
        return pagedResult.toList();
    }

    @Override
    public Role get(String name) {
        Role role = roleRepository.findByName(name);
        if (role == null) {
            throw new NotFoundExceptions("Role not Found");
        } else {
            return role;
        }
    }

    @Override
    public void save(Role role) {
        String temp = roleRepository.findByNameNQ(role.getName());
        if (temp != null) {
            throw new ConflictExceptions(String.format("There is a role with the same name ( %s ) ", temp));
        }
        roleRepository.save(role);
    }

    @Override
    public void update(String name, Role role) {
        Role updatingRole = roleRepository.findByName(name);

        if (updatingRole != null) {

            String temp = roleRepository.findByNameNQ(role.getName());
            if (temp != null && (!temp.equals(updatingRole.getName()))) {
                throw new ConflictExceptions(String.format("The role name ( %s ) exists by another role ", temp));
            }

            updatingRole.setName(role.getName());
            roleRepository.save(updatingRole);
        } else
            throw new NotFoundExceptions("Not found role to updating ");
    }

    @Override
    public void delete(String name) {
        Role role = roleRepository.findByName(name);

        if (role != null) {
            roleRepository.delete(role);
        } else {
            throw new NotFoundExceptions("Not found role to deleting ");
        }
    }

}
