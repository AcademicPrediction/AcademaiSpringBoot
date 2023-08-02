package com.tp2.academaispringboot.supervisor.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("supervisorMappingConfiguration")
public class MappingConfiguration {

    @Bean
    public SupervisorMapper supervisorMapper() {
        return new SupervisorMapper();
    }




}
