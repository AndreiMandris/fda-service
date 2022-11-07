package com.example.fdaservice.api;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DrugRecordQueryRequest {

  private String manufacturerName;
  private String brandName;
  private int pageNumber;
  private int pageSize;
}
