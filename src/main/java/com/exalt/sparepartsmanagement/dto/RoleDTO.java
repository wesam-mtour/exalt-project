package com.exalt.sparepartsmanagement.dto;

import com.exalt.sparepartsmanagement.model.Employee;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

public class RoleDTO {

    @NotNull
    private String name;

    public RoleDTO() {
    }

    //dfsdfsdfdsfsdfdfdfjjjjjjjjjjjjjjj
    //dsfsdfsdfsdf
    //weam

    public RoleDTO(@NotNull String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
