package com.example.portal.service.service;

import com.example.portal.service.model.ItemDetails;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @AUTHOR : Dinusha Ariyarathna
 * @DATE : 8/29/2024
 * @PROJECT : portal.service
 */
public interface RecognitionService {
    List<ItemDetails> detectLabels(InputStream inputStream) throws IOException;

    boolean isTargetObjectDetected(List<ItemDetails> items, String targetObjectName, float requiredConfidence);

}
