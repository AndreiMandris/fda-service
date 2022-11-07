package com.example.fdaservice.infrastructure.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FdaResult {
  private JsonNode submissions;

  @JsonProperty("application_number")
  private String applicationNumber;
  private OpenFda openfda;
  private JsonNode products;
}
