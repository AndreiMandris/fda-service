package com.example.fdaservice.implementation;

import com.example.fdaservice.api.DrugRecordQueryRequest;
import com.example.fdaservice.domain.DrugRecordApplicationPage;
import com.example.fdaservice.infrastructure.FdaApplicationFetcher;
import com.example.fdaservice.infrastructure.config.FdaConfigProp;
import com.example.fdaservice.infrastructure.model.FdaResultsDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;

@RunWith(MockitoJUnitRunner.class)
public class FdaApplicationFetcherTest {

  @Mock
  RestTemplate restTemplate;
  @Mock
  FdaConfigProp fdaConfigProp;

  @InjectMocks
  FdaApplicationFetcher applicationFetcher;

  @Test
  public void whenFdaDataIsQueried_thenResultsAreDisplayedInPages() throws IOException {
    restTemplate = Mockito.mock(RestTemplate.class);
    fdaConfigProp = Mockito.mock(FdaConfigProp.class);
    applicationFetcher = new FdaApplicationFetcher(restTemplate, fdaConfigProp);

    ObjectMapper objectMapper = new ObjectMapper();
    FdaResultsDto fdaResultsDtoSample = objectMapper.readValue(new File("src/test/resources/com/example/fdaservice/fda-data-page-0.json"), FdaResultsDto.class);

    Mockito.doReturn(ResponseEntity.ok(fdaResultsDtoSample)).when(restTemplate).getForEntity("https://www.test.url?search=manufacturer-name:Test-Labs&limit=0&skip=0", FdaResultsDto.class);
    Mockito.when(fdaConfigProp.getBaseUrl()).thenReturn("https://www.test.url");
    Mockito.when(fdaConfigProp.getManufacturerFieldName()).thenReturn("manufacturer-name");

    DrugRecordQueryRequest requestPage1 = DrugRecordQueryRequest.builder()
        .manufacturerName("Test-Labs")
        .pageNumber(0)
        .build();

    DrugRecordQueryRequest requestPage2 = DrugRecordQueryRequest.builder()
        .manufacturerName("Test-Labs")
        .pageNumber(1)
        .build();

    DrugRecordApplicationPage page1 = applicationFetcher.fetch(requestPage1);
    DrugRecordApplicationPage page2 = applicationFetcher.fetch(requestPage2);
    Assertions.assertNotNull(page1);
    Assertions.assertNotNull(page2);
  }
}
