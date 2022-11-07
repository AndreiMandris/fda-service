package com.example.fdaservice.domain;

import lombok.Data;

@Data
public class PageableRequest {
  private Integer pageNumber;
  private Integer pageSize;
}
