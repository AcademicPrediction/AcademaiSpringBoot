package com.tp2.academaispringboot.supervisor.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatePasswordResource {

        private String password;
        private String email;
}
