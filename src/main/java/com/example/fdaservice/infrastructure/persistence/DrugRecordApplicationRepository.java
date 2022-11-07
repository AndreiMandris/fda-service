package com.example.fdaservice.infrastructure.persistence;

import com.example.fdaservice.domain.DrugRecordApplication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrugRecordApplicationRepository extends JpaRepository<DrugRecordApplication, String> {
}
