package com.tp2.academaispringboot.email.dto;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailResource {

    private String name;
    private String email;
    @Size(min = 9, max = 9, message = "Phone must be 9 digits")
    private String phone;
    private String message;
}
