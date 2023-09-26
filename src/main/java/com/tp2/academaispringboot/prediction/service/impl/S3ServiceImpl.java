package com.tp2.academaispringboot.prediction.service.impl;

import com.tp2.academaispringboot.prediction.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.IOException;

@Service
public class S3ServiceImpl implements S3Service {


    @Autowired
    private S3Client s3Client;

    public String uploadFile(MultipartFile multipartFile, String fileName) throws IOException {
        try {
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket("bucket-academai-prediction-files")
                    .key(fileName)
                    .build();

            s3Client.putObject(putObjectRequest, RequestBody.fromBytes(multipartFile.getBytes()));
            return "File uploaded successfully";
        } catch (IOException e) {
            throw new IOException("Error uploading file to S3", e);
        }
    }

    @Override
    public byte[] downloadFile(String filename) throws IOException {
        if (!objectExists(filename)) {
            throw new RuntimeException("File not found");
        }

        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket("bucket-academai-prediction-files")
                .key(filename)
                .build();

        ResponseInputStream<GetObjectResponse> result = s3Client.getObject(getObjectRequest);
        return result.readAllBytes();
    }

    @Override
    public void deleteFile(String filename) {

    }

    @Override
    public boolean objectExists(String objectKey) {
        try {
            HeadObjectRequest headObjectRequest = HeadObjectRequest.builder()
                    .bucket("bucket-academai-prediction-files")
                    .key(objectKey)
                    .build();
            s3Client.headObject(headObjectRequest);
            return true;
        } catch (S3Exception e) {
            e.getStackTrace();
            if (e.statusCode() == 404) {
                return false;
            }
        }
        return false;
    }
}
