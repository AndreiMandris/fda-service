package com.example.fdaservice.infrastructure.persistence;

import com.example.fdaservice.application.DrugRecordApplicationService;
import com.example.fdaservice.domain.DrugRecordApplication;
import com.example.fdaservice.infrastructure.exception.ApplicationNotFoundException;
import com.example.fdaservice.infrastructure.exception.ResourceAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class DrugRecordApplicationServiceImpl implements DrugRecordApplicationService {

  private final DrugRecordApplicationRepository repository;

  @Override
  @Transactional
  public void createDrugRecordApplication(DrugRecordApplication drugRecordApplication) {
    if (repository.existsById(drugRecordApplication.getApplicationNumber())) {
      throw new ResourceAlreadyExistsException(
          String.format("Resource with application number: %s already exists", drugRecordApplication.getApplicationNumber()));
    }
    repository.save(drugRecordApplication);
  }

  @Override
  public DrugRecordApplication findById(String applicationNumber) {
    return repository.findById(applicationNumber).orElseThrow(() ->
        new ApplicationNotFoundException("Application not found by application number: " + applicationNumber));
  }
}
