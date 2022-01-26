package com.ericsson.placesapi.repository;

import com.ericsson.placesapi.entity.SearchRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SearchRecordRepository extends JpaRepository<SearchRecord, Long> {
    public Optional<SearchRecord> findByLatitudeAndLongitudeAndRadius(String latitude, String longitude, String radius);
}
