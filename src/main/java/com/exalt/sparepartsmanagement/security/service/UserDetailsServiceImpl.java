package com.exalt.sparepartsmanagement.security.service;

import com.exalt.sparepartsmanagement.model.Employee;
import com.exalt.sparepartsmanagement.repository.EmployeeRepository;
import com.exalt.sparepartsmanagement.security.model.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public class UserDetailsServiceImpl implements UserDetailsService {

    public static UserDetailsServiceImpl inst;

    @Autowired
    private EmployeeRepository employeeRepository;

    private List<String> roles;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        Employee user = employeeRepository.findUserByNameNQ(username);

        List<String> roles = (employeeRepository.findRolesByNameNQ(user.getName()));
        System.out.println("00000000000000000000000000000000000000");
        System.out.println(roles.get(0));
        setRoles(roles);
        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }
        inst = this;
        return new MyUserDetails(user);
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

}