package com.tp2.academaispringboot.prediction.mapping;


import com.tp2.academaispringboot.prediction.dto.CreatePredictionResource;
import com.tp2.academaispringboot.prediction.dto.PredictionResource;
import com.tp2.academaispringboot.prediction.model.PredictionEntity;
import com.tp2.academaispringboot.util.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

public class PredictionMapper implements Serializable {

    @Autowired
    private EnhancedModelMapper modelMapper;

    public PredictionResource toResource(PredictionEntity prediction) {
        return modelMapper.map(prediction, PredictionResource.class);
    }

    public List<PredictionResource> toResource(List<PredictionEntity> predictions) {
        return modelMapper.mapList(predictions, PredictionResource.class);
    }

    public PredictionEntity toEntity(PredictionResource prediction) {
        return modelMapper.map(prediction, PredictionEntity.class);
    }

    public PredictionEntity toEntity(CreatePredictionResource prediction) {
        return modelMapper.map(prediction, PredictionEntity.class);
    }
}
