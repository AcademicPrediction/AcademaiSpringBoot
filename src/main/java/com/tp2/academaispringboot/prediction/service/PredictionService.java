package com.tp2.academaispringboot.prediction.service;

import com.tp2.academaispringboot.prediction.model.PredictionEntity;

import java.util.List;

public interface PredictionService {
    PredictionEntity createPrediction(PredictionEntity predictionEntity, Long supervisorId);
    List<PredictionEntity> getPredictionsBySupervisorId(Long id);

}
