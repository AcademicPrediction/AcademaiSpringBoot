package com.tp2.academaispringboot.prediction.repository;

import com.tp2.academaispringboot.prediction.model.PredictionEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PredictionEntityRepository extends JpaRepository<PredictionEntity, Long> {
    List<PredictionEntity> findPredictionEntitiesBySupervisorEntityId(Long id);

    @Query(value = "SELECT * FROM prediction_entity WHERE supervisor_entity_id = ?1 ORDER BY id DESC LIMIT 1", nativeQuery = true)
    PredictionEntity findLastEntryBySupervisorId(Long supervisorId);
}