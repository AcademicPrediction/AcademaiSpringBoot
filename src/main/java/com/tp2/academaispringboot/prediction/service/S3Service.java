package com.tp2.academaispringboot.prediction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.S3Client;

import java.io.IOException;

public interface S3Service {

    public String uploadFile(MultipartFile multipartFile, String fileName) throws IOException;

    public byte[] downloadFile(String filename) throws IOException;

    public void deleteFile(String filename);

    public boolean objectExists(String objectKey);
}
