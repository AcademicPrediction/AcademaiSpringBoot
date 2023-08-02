package com.tp2.academaispringboot.prediction.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("predictionMappingConfiguration")
public class MappingConfiguration {

    @Bean
    public PredictionMapper predictionMapper() {
        return new PredictionMapper();
    }
}
