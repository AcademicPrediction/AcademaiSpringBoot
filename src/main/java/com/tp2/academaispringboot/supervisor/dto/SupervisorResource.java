package com.tp2.academaispringboot.supervisor.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SupervisorResource {

    private Long id;
    private String name;
    private String lastName;
    private String email;
    private String dni;
    private String password;
    private String phoneNumber;

}
