package com.tp2.academaispringboot.prediction.controller;

import com.tp2.academaispringboot.prediction.dto.CreatePredictionResource;
import com.tp2.academaispringboot.prediction.dto.PredictionResource;
import com.tp2.academaispringboot.prediction.mapping.PredictionMapper;
import com.tp2.academaispringboot.prediction.service.PredictionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Prediction", description = "Prediction API")
@RestController
@RequestMapping("/api/v1/predictions")
@CrossOrigin
public class PredictionController {

    @Autowired
    private PredictionService predictionService;

    @Autowired
    private PredictionMapper predictionMapper;

    @Operation(summary = "Get all predictions by supervisor", description = "Get all predictions by supervisor id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all predictions by supervisor"),
            @ApiResponse(responseCode = "404", description = "Predictions by supervisor not found")})
    @GetMapping("/supervisor/{supervisorId}")
    public List<PredictionResource> getAllPredictionsBySupervisor(@PathVariable(name = "supervisorId") Long id) {
        return predictionMapper.toResource(predictionService.getPredictionsBySupervisorId(id));
    }

    @Operation(summary = "Create prediction", description = "Create prediction")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Created prediction"),
            @ApiResponse(responseCode = "404", description = "Prediction not created")})
    @PostMapping("/create/{supervisorId}")
    public PredictionResource createPrediction(@PathVariable(name = "supervisorId") Long supervisorId,
                                               @RequestBody CreatePredictionResource createPredictionResource) {
        return predictionMapper.toResource(predictionService.createPrediction(predictionMapper.toEntity(createPredictionResource), supervisorId));
    }
}
