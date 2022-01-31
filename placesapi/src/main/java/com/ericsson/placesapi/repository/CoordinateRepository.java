package com.ericsson.placesapi.repository;

import com.ericsson.placesapi.entity.Coordinate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CoordinateRepository extends JpaRepository<Coordinate, String> {
    public Optional<Coordinate> findByLatitudeAndLongitude(Float latitude, Float longitude);
}
