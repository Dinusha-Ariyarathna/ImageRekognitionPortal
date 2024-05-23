package com.example.portal.service.config;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.rekognition.RekognitionClient;

@Configuration
public class AwsConfig {

    @Autowired
    private AwsProperties aws;

    @Bean
    public RekognitionClient rekognitionClient() {
        AwsBasicCredentials awsCredentials = AwsBasicCredentials.create(aws.getAccessKey(), aws.getSecretKey());

        return RekognitionClient.builder()
                .region(Region.of(aws.getRegion()))
                .credentialsProvider(() -> awsCredentials)
                .build();
    }


}
