package com.exalt.sparepartsmanagement.mapper;


import com.exalt.sparepartsmanagement.dto.RoleDTO;
import com.exalt.sparepartsmanagement.model.Role;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    List<Role> DTOsToRoles(List<RoleDTO> roleDTO);

    List<RoleDTO> RolesToDTOs(List<Role> roles);

    RoleDTO RoleToDTO(Role role);


}
