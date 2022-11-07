package com.example.fdaservice.infrastructure;

import com.example.fdaservice.domain.DrugRecordApplication;
import com.example.fdaservice.domain.DrugRecordApplicationPage;
import com.example.fdaservice.infrastructure.model.FdaResult;
import com.example.fdaservice.infrastructure.model.FdaResultsDto;
import com.example.fdaservice.infrastructure.model.OpenFda;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FdaApplicationMapper {

  public static List<DrugRecordApplication> toApplications(FdaResultsDto fdaResultsDto) {
    return null;
  }

  public static DrugRecordApplicationPage toApplicationPage(FdaResultsDto fdaResultsDto) {
    List<DrugRecordApplication> drugRecordApplicationList = fdaResultsDto.getResults()
        .stream()
        .map(FdaApplicationMapper::toApplication)
        .collect(Collectors.toList());

    Optional<JsonNode> resultsMeta = Optional.ofNullable(fdaResultsDto.getMeta())
        .map(e -> e.get("results"));

    int totalElements = resultsMeta
        .map(e -> e.get("total"))
        .map(JsonNode::intValue)
        .orElseThrow();

    int limit = resultsMeta
        .map(e -> e.get("limit"))
        .map(JsonNode::intValue)
        .orElseThrow();

    int skip = resultsMeta
        .map(e -> e.get("skip"))
        .map(JsonNode::intValue)
        .orElseThrow();

    int totalPages = (int) Math.ceil((double) totalElements / limit);
    int currentPage = skip / limit;

    return DrugRecordApplicationPage.builder()
        .drugRecordApplications(drugRecordApplicationList)
        .totalPages(totalPages)
        .currentPage(currentPage)
        .build();
  }

  private static DrugRecordApplication toApplication(FdaResult fdaResult) {
    return DrugRecordApplication.builder()
        .applicationNumber(fdaResult.getApplicationNumber())
        .submissions(fdaResult.getSubmissions())
        .brands(Optional.ofNullable(fdaResult.getOpenfda()).map(OpenFda::getBrandName).orElse(null))
        .manufacturers(Optional.ofNullable(fdaResult.getOpenfda()).map(OpenFda::getManufacturerName).orElse(null))
        .build();
  }
}
