package com.tp2.academaispringboot.administrator.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("administratorMappingConfiguration")
public class MappingConfiguration {

    @Bean
    public AdministratorMapper administratorMapper() {
        return new AdministratorMapper();
    }
}
