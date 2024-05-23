package com.example.portal.service.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
@Getter
@Setter
public class AwsProperties {

    @Value("${spring.application.aws.access-key}")
    private String accessKey;
    @Value("${spring.application.aws.secret-key}")
    private String secretKey;
    @Value("${spring.application.aws.region}")
    private String region;
    private String endpoint;
    private String s3BucketName;


}
