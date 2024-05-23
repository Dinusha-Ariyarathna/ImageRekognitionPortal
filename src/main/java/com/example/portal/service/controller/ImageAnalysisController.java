package com.example.portal.service.controller;


import com.example.portal.service.model.ItemDetails;
import com.example.portal.service.service.RecognitionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.rekognition.RekognitionClient;
import software.amazon.awssdk.services.rekognition.model.Label;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @AUTHOR : Dinusha Ariyarathna
 * @DATE : 5/23/2024
 * @PROJECT : portal.service
 */
@RestController
public class ImageAnalysisController {

    @Autowired
    RecognitionServiceImpl recognitionService;

    @PostMapping("/analyze")
    public ResponseEntity<List<ItemDetails>> analyzeImage(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }
        try {
            List<ItemDetails> labels = recognitionService.detectLabels(file.getInputStream());
            return ResponseEntity.ok(labels);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
