package com.example.portal.service.service;

import com.example.portal.service.config.AwsConfig;
import com.example.portal.service.model.ItemDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.rekognition.RekognitionClient;
import software.amazon.awssdk.services.rekognition.model.DetectLabelsRequest;
import software.amazon.awssdk.services.rekognition.model.DetectLabelsResponse;
import software.amazon.awssdk.services.rekognition.model.Image;
import software.amazon.awssdk.services.rekognition.model.Label;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @AUTHOR : Dinusha Ariyarathna
 * @DATE : 5/23/2024
 * @PROJECT : portal.service
 */
@Service
public class RecognitionServiceImpl {

    private final RekognitionClient rekognitionClient;

    public RecognitionServiceImpl(RekognitionClient rekognitionClient) {
        this.rekognitionClient = rekognitionClient;
    }

    public List<ItemDetails> detectLabels(InputStream inputStream) throws IOException {

        Image image = Image.builder()
                .bytes(SdkBytes.fromInputStream(inputStream))
                .build();

        DetectLabelsRequest request = DetectLabelsRequest.builder()
                .image(image)
                .maxLabels(10)
                .minConfidence(75F)
                .build();

        DetectLabelsResponse response = rekognitionClient.detectLabels(request);
//        return response.labels();
        List<Label> labels = response.labels();
        System.out.println("Detected labels for the given photo");
        ArrayList<ItemDetails> list = new ArrayList<>();
        ItemDetails item ;
        for (Label label: labels) {
            item = new ItemDetails();
//            item.setKey(key); // identifies the photo
            item.setConfidence(label.confidence().toString());
            item.setName(label.name());
            list.add(item);
        }
        return list;
    }
}
