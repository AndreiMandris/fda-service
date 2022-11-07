package com.example.fdaservice.infrastructure.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "fda.api")
public class FdaConfigProp {
  private String baseUrl;
  private String manufacturerFieldName;
  private String brandFieldName;
  private String key;
}
