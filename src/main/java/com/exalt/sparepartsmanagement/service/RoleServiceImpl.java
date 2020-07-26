package com.exalt.sparepartsmanagement.service;


import com.exalt.sparepartsmanagement.dto.RoleDTO;
import com.exalt.sparepartsmanagement.mapper.RoleMapper;
import com.exalt.sparepartsmanagement.repository.RoleRepository;
import com.exalt.sparepartsmanagement.error.exceptions.ConflictExceptions;
import com.exalt.sparepartsmanagement.error.exceptions.NotFoundExceptions;
import com.exalt.sparepartsmanagement.model.Role;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;
    private RoleMapper roleMapper = Mappers.getMapper(RoleMapper.class);


    @Override
    @Transactional(readOnly = true)
    public List<RoleDTO> getAll(int page , int pageSize)
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
        return roleMapper.RolesToDTOs(pagedResult.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public RoleDTO get(String name) {
        Role role = roleRepository.findByName(name);
        if (role == null) {
            throw new NotFoundExceptions("Role not Found");
        } else {
            return roleMapper.RoleToDTO(role);
        }
    }

    @Override
    @Transactional
    public void save(RoleDTO roleDTO) {
        String temp = roleRepository.findByNameNQ(roleDTO.getName());
        if (temp != null) {
            throw new ConflictExceptions(String.format("There is a role with the same name ( %s ) ", temp));
        }
        roleRepository.save(roleMapper.TDOToRole(roleDTO));
    }

    @Override
    @Transactional
    public void update(String name, RoleDTO roleDTO) {
        Role updatingRole = roleRepository.findByName(name);

        if (updatingRole != null) {

            String temp = roleRepository.findByNameNQ(roleDTO.getName());
            if (temp != null && (!temp.equals(updatingRole.getName()))) {
                throw new ConflictExceptions(String.format("The role name ( %s ) exists by another role ", temp));
            }

            updatingRole.setName(roleDTO.getName());
            roleRepository.save(updatingRole);
        } else
            throw new NotFoundExceptions("Not found role to updating ");
    }

    @Override
    @Transactional
    public void delete(String name) {
        Role role = roleRepository.findByName(name);

        if (role != null) {
            roleRepository.delete(role);
        } else {
            throw new NotFoundExceptions("Not found role to deleting ");
        }
    }

}
