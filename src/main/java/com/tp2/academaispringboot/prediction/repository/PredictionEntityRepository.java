package com.tp2.academaispringboot.prediction.repository;

import com.tp2.academaispringboot.prediction.model.PredictionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PredictionEntityRepository extends JpaRepository<PredictionEntity, Long> {
    List<PredictionEntity> findPredictionEntitiesBySupervisorEntityId(Long id);
}