package com.example.fdaservice.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DrugRecordApplicationDto {

  private String applicationNumber;
  private String manufacturerName;
  private String substanceName;
  private List<String> productNumbers;
}
