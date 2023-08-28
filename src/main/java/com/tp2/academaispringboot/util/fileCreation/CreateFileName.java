package com.tp2.academaispringboot.util.fileCreation;

public class CreateFileName {
    public static String createFileName(String predictionId, String fileName) {
        return predictionId + "_" + fileName;
    }

}
