package com.example.fdaservice.application;

import com.example.fdaservice.domain.DrugRecordApplication;

public interface DrugRecordApplicationService {
  void createDrugRecordApplication(DrugRecordApplication drugRecordApplication);

  DrugRecordApplication findById(String applicationNumber);
}
