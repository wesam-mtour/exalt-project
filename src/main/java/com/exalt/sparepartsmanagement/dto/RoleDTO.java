package com.exalt.sparepartsmanagement.dto;

import javax.validation.constraints.NotNull;

public class RoleDTO {

    @NotNull
    private String name;

    public RoleDTO() {
    }


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
