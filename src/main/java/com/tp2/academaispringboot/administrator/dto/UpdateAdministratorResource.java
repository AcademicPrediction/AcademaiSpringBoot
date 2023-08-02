package com.tp2.academaispringboot.administrator.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateAdministratorResource {
    private String name;
    private String lastName;
    private String email;
}
