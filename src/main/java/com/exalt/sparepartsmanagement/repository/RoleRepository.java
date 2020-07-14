package com.exalt.sparepartsmanagement.repository;

import com.exalt.sparepartsmanagement.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByName(String name);

    @Query(value = "select r.name from role r where r.name=:name", nativeQuery = true)
    String findByNameNQ(String name);


}
