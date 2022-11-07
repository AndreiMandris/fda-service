package com.example.fdaservice.implementation;

import com.example.fdaservice.application.DrugRecordApplicationService;
import com.example.fdaservice.domain.DrugRecordApplication;
import com.example.fdaservice.infrastructure.exception.ResourceAlreadyExistsException;
import com.example.fdaservice.infrastructure.persistence.DrugRecordApplicationRepository;
import com.example.fdaservice.infrastructure.persistence.DrugRecordApplicationServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class DrugRecordApplicationServiceTest {

  @Mock
  DrugRecordApplicationRepository repository;

  DrugRecordApplicationService drugRecordApplicationService;

  @Test
  public void whenNewApplicationIsSubmited_thenItIsSaved() {
    repository = Mockito.mock(DrugRecordApplicationRepository.class);
    drugRecordApplicationService = new DrugRecordApplicationServiceImpl(repository);

    String applicationNumber = "TEST123";
    Mockito.when(repository.existsById(applicationNumber)).thenReturn(false);

    DrugRecordApplication drugRecordApplication = DrugRecordApplication.builder()
        .applicationNumber(applicationNumber)
        .build();

    drugRecordApplicationService.createDrugRecordApplication(drugRecordApplication);

    Mockito.verify(repository).existsById(applicationNumber);
    Mockito.verify(repository).save(drugRecordApplication);
  }

  @Test
  public void whenDuplicateApplicationNumberIsSubmited_thenExceptionIsThrown() {
    repository = Mockito.mock(DrugRecordApplicationRepository.class);
    drugRecordApplicationService = new DrugRecordApplicationServiceImpl(repository);

    String applicationNumber = "TEST123";
    Mockito.when(repository.existsById(applicationNumber)).thenReturn(true);

    DrugRecordApplication drugRecordApplication = DrugRecordApplication.builder()
        .applicationNumber(applicationNumber)
        .build();

    assertThrows(ResourceAlreadyExistsException.class,
        () -> drugRecordApplicationService.createDrugRecordApplication(drugRecordApplication));

    Mockito.verify(repository).existsById(applicationNumber);
    Mockito.verify(repository, times(0)).save(drugRecordApplication);
  }
}
