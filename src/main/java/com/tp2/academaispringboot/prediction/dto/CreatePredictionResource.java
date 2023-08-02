package com.tp2.academaispringboot.prediction.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public class CreatePredictionResource {

    private String name;
    private String values;
    private String results;
}
