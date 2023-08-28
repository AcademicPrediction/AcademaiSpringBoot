package com.tp2.academaispringboot.prediction.controller;

import com.tp2.academaispringboot.prediction.dto.CreatePredictionResource;
import com.tp2.academaispringboot.prediction.dto.GetPredictionResource;
import com.tp2.academaispringboot.prediction.dto.PredictionResource;
import com.tp2.academaispringboot.prediction.mapping.PredictionMapper;
import com.tp2.academaispringboot.prediction.model.PredictionEntity;
import com.tp2.academaispringboot.prediction.service.PredictionService;
import com.tp2.academaispringboot.prediction.service.S3Service;
import com.tp2.academaispringboot.util.fileCreation.CreateFileName;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;

@Tag(name = "Prediction", description = "Prediction API")
@RestController
@RequestMapping("/api/v1/predictions")
@CrossOrigin
public class PredictionController {

    @Value("${flaskapi.url.parameters}")
    private String flaskApiUrlParameters;

    @Autowired
    private PredictionService predictionService;

    @Autowired
    private PredictionMapper predictionMapper;

    @Autowired
    private S3Service s3Service;

    @Operation(summary = "Get all predictions by supervisor", description = "Get all predictions by supervisor id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all predictions by supervisor"),
            @ApiResponse(responseCode = "404", description = "Predictions by supervisor not found")})
    @GetMapping("/{supervisorId}")
    public List<PredictionResource> getAllPredictionsBySupervisor(@PathVariable(name = "supervisorId") Long id) {
        return predictionMapper.toResource(predictionService.getPredictionsBySupervisorId(id));
    }

    @PostMapping("/do-prediction/{supervisorId}")
    public ResponseEntity<ByteArrayResource> doPrediction(@RequestParam("file") MultipartFile multipartFile, @PathVariable("supervisorId") Long supervisorId) throws IOException {

        CreatePredictionResource createPredictionResource = new CreatePredictionResource();
        createPredictionResource.setName(multipartFile.getOriginalFilename());

        PredictionEntity predictionEntity = predictionService.createPrediction(predictionMapper.toEntity(createPredictionResource), supervisorId);

        String url = flaskApiUrlParameters;
        RestTemplate restTemplate = new RestTemplate();

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", multipartFile.getResource());
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, new HttpHeaders());

        ResponseEntity<byte[]> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                byte[].class
        );
        MultipartFile filePredict = createFile(response.getBody(), multipartFile);

        s3Service.uploadFile(filePredict, CreateFileName.createFileName(predictionEntity.getId().toString(), predictionEntity.getName()));


        ByteArrayResource resource = new ByteArrayResource(Objects.requireNonNull(response.getBody()));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + predictionEntity.getName())
                .body(resource);
    }

    @PostMapping("/download")
    public ResponseEntity<ByteArrayResource> downloadFile(@RequestBody GetPredictionResource getPredictionResource) throws IOException {
        byte[] data = s3Service.downloadFile(CreateFileName.createFileName(getPredictionResource.getId().toString(), getPredictionResource.getName()));
        ByteArrayResource resource = new ByteArrayResource(data);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + getPredictionResource.getName())
                .body(resource);
    }

    public MultipartFile createFile(byte[] data, MultipartFile currentFile) {
        return new MultipartFile() {
            @Override
            public String getName() {
                return currentFile.getName();
            }

            @Override
            public String getOriginalFilename() {
                return currentFile.getOriginalFilename();
            }

            @Override
            public String getContentType() {
                return currentFile.getContentType();
            }

            @Override
            public boolean isEmpty() {
                return currentFile.isEmpty();
            }

            @Override
            public long getSize() {
                return currentFile.getSize();
            }

            @Override
            public byte[] getBytes() throws IOException {
                return data;
            }

            @Override
            public InputStream getInputStream() throws IOException {
                return currentFile.getInputStream();
            }

            @Override
            public void transferTo(File dest) throws IOException, IllegalStateException {
                currentFile.transferTo(dest);
            }
        };

    }

}
