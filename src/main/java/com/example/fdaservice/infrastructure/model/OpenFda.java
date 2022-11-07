package com.example.fdaservice.infrastructure.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenFda {
  @JsonProperty("application_number")
  private List<String> applicationNumber;

  @JsonProperty("brand_name")
  private List<String> brandName;

  @JsonProperty("manufacturer_name")
  private List<String> manufacturerName;

  private List<String> route;

  @JsonProperty("substance_name")
  private List<String> substanceName;
}
