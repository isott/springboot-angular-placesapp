package com.ericsson.placesapi.service;

import com.ericsson.placesapi.entity.SearchRecord;
import com.ericsson.placesapi.repository.SearchRecordRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class SearchRecordService {

    private final SearchRecordRepository searchRecordRepository;

    public SearchRecordService(SearchRecordRepository searchRecordRepository) {
        this.searchRecordRepository = searchRecordRepository;
    }

    public Optional<SearchRecord> isSearchedBefore(String latitude, String longitude, String radius) {
        return searchRecordRepository.findByLatitudeAndLongitudeAndRadius(latitude, longitude, radius);
    }

    public SearchRecord buildSearchRecord(String latitude, String longitude, String radius, Set coordinateSet) {
        SearchRecord searchRecord = new SearchRecord();
        searchRecord.setLatitude(latitude);
        searchRecord.setLongitude(longitude);
        searchRecord.setRadius(radius);
        searchRecord.setCoordinateList(coordinateSet);

        return searchRecord;
    }

    public void saveSearchRecord(SearchRecord searchRecord) {
        searchRecordRepository.save(searchRecord);
    }
}
