package com.example.fdaservice.application;

import com.example.fdaservice.api.DrugRecordQueryRequest;
import com.example.fdaservice.domain.DrugRecordApplicationPage;

public interface ApplicationFetcher {
  DrugRecordApplicationPage fetch(DrugRecordQueryRequest drugRecordQueryRequest);
}
