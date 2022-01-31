package com.ericsson.placesapi.service;

import com.ericsson.placesapi.client.IPlacesClient;
import com.ericsson.placesapi.client.PlacesClient;
import com.ericsson.placesapi.entity.Coordinate;
import com.ericsson.placesapi.entity.SearchRecord;
import com.ericsson.placesapi.exeption.NotFoundException;
import com.ericsson.placesapi.entity.model.Location;
import com.ericsson.placesapi.entity.model.Result;
import com.ericsson.placesapi.entity.model.SearchDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PlacesService {
    private final IPlacesClient placesClient;
    private final CoordinateService coordinateService;
    private final SearchRecordService searchRecordService;

    @Autowired
    public PlacesService(PlacesClient placesClient,
                         CoordinateService coordinateService,
                         SearchRecordService searchRecordService) {
        this.placesClient = placesClient;
        this.coordinateService = coordinateService;
        this.searchRecordService = searchRecordService;
    }


    public Set<Coordinate> getUnsavedCoordinates(Set<Coordinate> coordinateSet) {
        if (coordinateSet.isEmpty()) {
            return new HashSet<>();
        }
        return coordinateSet.stream().filter(e -> !coordinateService.isCoordinateAlreadyExist(e.getLatitude(), e.getLongitude())).collect(Collectors.toSet());
    }

    public List<Location> coordinatesToLocations(Set<Coordinate> coordinates) {
        if (coordinates == null) {
            return new ArrayList<>();
        }
        return coordinates.stream().map(e -> new Location(e.getLatitude(), e.getLongitude())).collect(Collectors.toList());
    }

    public List<Location> nearbySearch(SearchDTO search) {
        Optional<SearchRecord> records = searchRecordService.isSearchedBefore(search);
        if (records.isPresent()) {
            log.info("This coordinate has been searched before. So the data fetched from db. ", search);
            return coordinatesToLocations(records.get().getCoordinateList());
        }

        List<Result> resultList = placesClient.nearBySearch(search);
        if(resultList.isEmpty()) {
            log.info("This search has no result. ", search);
            throw new NotFoundException(search);
        }

        Set<Coordinate> coordinateSet = coordinateService.getCoordinatesFromOutcome(resultList);

        SearchRecord searchRecord = searchRecordService.buildSearchRecord(search, getUnsavedCoordinates(coordinateSet));
        searchRecordService.saveSearchRecord(searchRecord);

        return coordinatesToLocations(coordinateSet);
    }
}
