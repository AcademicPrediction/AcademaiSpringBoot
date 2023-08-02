package com.tp2.academaispringboot.prediction.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public class PredictionResource {

    private Long id;
    private String name;
    private String values;
    private String results;
    private Date date;
}
