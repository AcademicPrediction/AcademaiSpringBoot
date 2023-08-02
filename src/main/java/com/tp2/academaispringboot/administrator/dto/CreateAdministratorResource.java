package com.tp2.academaispringboot.administrator.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAdministratorResource {
    private String name;
    private String lastName;
    private String password;
    private String email;
}
