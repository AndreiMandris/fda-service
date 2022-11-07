package com.example.fdaservice.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DrugRecordApplicationPage {
  private int currentPage;
  private int totalPages;
  private List<DrugRecordApplication> drugRecordApplications;
}
