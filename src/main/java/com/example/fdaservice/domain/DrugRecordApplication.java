package com.example.fdaservice.domain;

import com.fasterxml.jackson.databind.JsonNode;
import com.vladmihalcea.hibernate.type.array.ListArrayType;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "DRUG_RECORD_APPLICATION")
@TypeDefs(
    {@TypeDef(
        name = "list-array",
        typeClass = ListArrayType.class),
        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)}
)
public class DrugRecordApplication {

  @Id
  @Column(name = "APPLICATION_NUMBER")
  private String applicationNumber;

  @Transient
  private JsonNode submissions;

  @Transient
  private List<String> brands;

  @Type(type = "list-array")
  @Column(
      name = "manufacturers",
      columnDefinition = "text[]"
  )
  private List<String> manufacturers;

  @Column
  private String substance;

  @Type(type = "list-array")
  @Column(
      name = "PRODUCT_NUMBERS",
      columnDefinition = "text[]"
  )
  private List<String> productNumbers;
}
