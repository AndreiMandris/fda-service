package com.example.fdaservice.api;

import com.example.fdaservice.application.ApplicationFetcher;
import com.example.fdaservice.application.DrugRecordApplicationService;
import com.example.fdaservice.domain.DrugRecordApplicationPage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/application")
public class DrugRecordApplicationController {

  private final ApplicationFetcher applicationFetcher;
  private final DrugRecordApplicationService drugRecordApplicationService;

  @GetMapping
  public ResponseEntity<DrugRecordApplicationPage> getApplications(@RequestParam String manufacturerName,
                                                                   @RequestParam(required = false) String brandName,
                                                                   @RequestParam Integer pageNumber,
                                                                   @RequestParam(required = false) Integer pageSize) {
    DrugRecordQueryRequest drugRecordQueryRequest = DrugRecordQueryRequest.builder()
        .manufacturerName(manufacturerName)
        .brandName(brandName)
        .pageSize(Optional.ofNullable(pageSize).orElse(10))
        .pageNumber(pageNumber)
        .build();

    return ResponseEntity.ok(applicationFetcher.fetch(drugRecordQueryRequest));
  }

  @PostMapping
  public ResponseEntity<?> createApplication(@RequestBody DrugRecordApplicationDto drugRecordApplicationDto) {
    drugRecordApplicationService.createDrugRecordApplication(DtoMapper.toDrugRecordApplication(drugRecordApplicationDto));
    return ResponseEntity.status(201).body(drugRecordApplicationDto.getApplicationNumber());
  }

  @GetMapping("/{applicationNumber}")
  public ResponseEntity<?> getApplication(@PathVariable("applicationNumber") String applicationNumber) {
    return ResponseEntity.ok(
        DtoMapper.toDrugRecordApplicationDto(drugRecordApplicationService.findById(applicationNumber)));
  }
}
