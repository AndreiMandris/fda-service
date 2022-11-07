package com.example.fdaservice.infrastructure;


import com.example.fdaservice.api.DrugRecordQueryRequest;
import com.example.fdaservice.application.ApplicationFetcher;
import com.example.fdaservice.domain.DrugRecordApplicationPage;
import com.example.fdaservice.infrastructure.config.FdaConfigProp;
import com.example.fdaservice.infrastructure.model.FdaResultsDto;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Data
@Slf4j
@Service
@RequiredArgsConstructor
public class FdaApplicationFetcher implements ApplicationFetcher {

  private final RestTemplate restTemplate;
  private final FdaConfigProp fdaConfigProp;

  @Override
  @Cacheable(value = "fda-cache", key = "#drugRecordQueryRequest")
  @Retryable(value = HttpServerErrorException.class,
      backoff = @Backoff(delay = 100L, maxDelay = 1500L, random = true, multiplier = 0.3))
  public DrugRecordApplicationPage fetch(DrugRecordQueryRequest drugRecordQueryRequest) {
    log.debug("Calling {} for manufacturer: {}, brand: {}, pageSize: {}, pageNo: {}",
        fdaConfigProp.getBaseUrl(), drugRecordQueryRequest.getManufacturerName(), drugRecordQueryRequest.getBrandName(),
        drugRecordQueryRequest.getPageSize(), drugRecordQueryRequest.getPageNumber());

    ResponseEntity<FdaResultsDto> response = restTemplate.getForEntity(
        getUriComponentsBuilder(drugRecordQueryRequest).toUriString(),
        FdaResultsDto.class);

    if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
      return FdaApplicationMapper.toApplicationPage(response.getBody());
    } else {
      throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  private UriComponentsBuilder getUriComponentsBuilder(DrugRecordQueryRequest drugRecordQueryRequest) {
    StringBuilder searchQuery = new StringBuilder();
    searchQuery.append(fdaConfigProp.getManufacturerFieldName()).append(":")
        .append(drugRecordQueryRequest.getManufacturerName());

    if (drugRecordQueryRequest.getBrandName() != null) {
      searchQuery.append("+AND+").append(fdaConfigProp.getBrandFieldName()).append(":")
          .append(drugRecordQueryRequest.getBrandName());
    }
    return UriComponentsBuilder.fromHttpUrl(fdaConfigProp.getBaseUrl())
        .queryParam("search", searchQuery)
        .queryParam("limit", drugRecordQueryRequest.getPageSize())
        .queryParam("skip", drugRecordQueryRequest.getPageNumber() * drugRecordQueryRequest.getPageSize());
  }
}
