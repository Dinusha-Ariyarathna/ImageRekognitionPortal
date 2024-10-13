package com.example.portal.service.controller;


import com.example.portal.service.model.ItemDetails;
import com.example.portal.service.service.RecognitionService;
import com.example.portal.service.service.impl.RecognitionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


/**
 * @AUTHOR : Dinusha Ariyarathna
 * @DATE : 5/23/2024
 * @PROJECT : portal.service
 */
@RestController
public class ImageAnalysisController {

    @Autowired
    RecognitionService recognitionService;

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

    @GetMapping("/check")
    public String check() {
        return "Hello World";
    }
}
