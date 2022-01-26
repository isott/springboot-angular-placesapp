package com.ericsson.placesapi.service;

import com.ericsson.placesapi.entity.Coordinate;
import com.ericsson.placesapi.model.Outcome;
import com.ericsson.placesapi.model.Result;
import com.ericsson.placesapi.repository.CoordinateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CoordinateService {
    private final CoordinateRepository coordinateRepository;

    @Autowired
    public CoordinateService(CoordinateRepository coordinateRepository) {
        this.coordinateRepository = coordinateRepository;
    }

    public Set<Coordinate> getCoordinatesFromOutcome(List<Result> results) {
        if (results.isEmpty()) {
            return null;
        }

        return results.stream()
                .map(e ->
                        new Coordinate(e.getPlaceId(),
                                e.getGeometry().getLocation().getLatitude(),
                                e.getGeometry().getLocation().getLongitude(), null))
                .collect(Collectors.toSet());
    }

    public boolean isCoordinateAlreadyExist(String latitude, String longitude) {
        Optional<Coordinate> temp = coordinateRepository.findByLatitudeAndLongitude(latitude, longitude);
        return temp.isPresent();
    }
}
