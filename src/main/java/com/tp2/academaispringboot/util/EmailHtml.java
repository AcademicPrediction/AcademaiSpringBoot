package com.tp2.academaispringboot.util;

import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class EmailHtml {

    public static String readHtmlFile(String path) throws IOException {
        ClassPathResource resource = new ClassPathResource(path);
        byte[] bytes = Files.readAllBytes(Paths.get(resource.getURI()));
        return new String(bytes, StandardCharsets.UTF_8);
    }
    public static String generateHtmlContent(String userName, String userEmail, String userPhone, String userMessage) {
        return "<html>" +
                "<head>" +
                "<style>" +
                "body { font-family: Arial, sans-serif; margin: 20px; padding: 0; background-color: #f4f4f4; }" +
                ".container { background-color: #fff; border: 1px solid #ccc; border-radius: 8px; padding: 20px; margin: 20px auto; max-width: 600px; }" +
                "h1 { color: #333366; font-size: 24px; }" +
                "p { font-size: 16px; }" +
                ".label { font-weight: bold; }" +
                "</style>" +
                "</head>" +
                "<body>" +
                "<div class='container'>" +
                "<h1>Nuevo mensaje de Contáctanos</h1>" +
                "<p><span class='label'>Nombre del Usuario:</span> " + userName + "</p>" +
                "<p><span class='label'>Email del Usuario:</span> " + userEmail + "</p>" +
                "<p><span class='label'>Teléfono del Usuario:</span> " + userPhone + "</p>" +
                "<p><span class='label'>Mensaje:</span></p>" +
                "<p>" + userMessage + "</p>" +
                "</div>" +
                "</body>" +
                "</html>";
    }
}
