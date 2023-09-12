package com.tp2.academaispringboot.prediction.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PredictionNotFoundResource {

    private String message;
}
