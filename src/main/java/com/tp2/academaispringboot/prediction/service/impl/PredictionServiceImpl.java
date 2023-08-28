package com.tp2.academaispringboot.prediction.service.impl;

import com.tp2.academaispringboot.prediction.model.PredictionEntity;
import com.tp2.academaispringboot.prediction.repository.PredictionEntityRepository;
import com.tp2.academaispringboot.prediction.service.PredictionService;
import com.tp2.academaispringboot.supervisor.repository.SupervisorEntityRepository;
import com.tp2.academaispringboot.util.fileCreation.CreateFileName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class PredictionServiceImpl implements PredictionService {

    @Autowired
    private PredictionEntityRepository predictionEntityRepository;

    @Autowired
    private SupervisorEntityRepository supervisorEntityRepository;

    @Override
    public PredictionEntity createPrediction(PredictionEntity predictionEntity, Long supervisorId) {
        predictionEntity.setSupervisorEntity(supervisorEntityRepository.findById(supervisorId).orElseThrow());
        predictionEntity.setDate(new Date());
        return predictionEntityRepository.save(predictionEntity);
    }

    @Override
    public List<PredictionEntity> getPredictionsBySupervisorId(Long id) {
        return predictionEntityRepository.findPredictionEntitiesBySupervisorEntityId(id);
    }
}
