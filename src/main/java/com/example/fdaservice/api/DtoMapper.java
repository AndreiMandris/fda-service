package com.example.fdaservice.api;

import com.example.fdaservice.domain.DrugRecordApplication;

import java.util.List;

public class DtoMapper {
  public static DrugRecordApplication toDrugRecordApplication(DrugRecordApplicationDto drugRecordApplicationDto) {
    return DrugRecordApplication.builder()
        .applicationNumber(drugRecordApplicationDto.getApplicationNumber())
        .manufacturers(List.of(drugRecordApplicationDto.getManufacturerName()))
        .substance(drugRecordApplicationDto.getSubstanceName())
        .productNumbers(drugRecordApplicationDto.getProductNumbers())
        .build();
  }

  public static DrugRecordApplicationDto toDrugRecordApplicationDto(DrugRecordApplication drugRecordApplication) {
    return DrugRecordApplicationDto.builder()
        .applicationNumber(drugRecordApplication.getApplicationNumber())
        .productNumbers(drugRecordApplication.getProductNumbers())
        .substanceName(drugRecordApplication.getSubstance())
        .manufacturerName(drugRecordApplication.getManufacturers().stream().findAny().orElse(null))
        .build();
  }
}
