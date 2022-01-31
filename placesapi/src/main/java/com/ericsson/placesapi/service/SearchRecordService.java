package com.ericsson.placesapi.service;

import com.ericsson.placesapi.entity.Coordinate;
import com.ericsson.placesapi.entity.SearchRecord;
import com.ericsson.placesapi.entity.model.SearchDTO;
import com.ericsson.placesapi.repository.SearchRecordRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class SearchRecordService {

    private final SearchRecordRepository searchRecordRepository;

    @Autowired
    public SearchRecordService(SearchRecordRepository searchRecordRepository) {
        this.searchRecordRepository = searchRecordRepository;
    }

    public Optional<SearchRecord> isSearchedBefore(SearchDTO search) {
        return searchRecordRepository.findByLatitudeAndLongitudeAndRadius(search.getLatitude(), search.getLongitude(), search.getRadius());
    }

    public SearchRecord buildSearchRecord(SearchDTO search, Set<Coordinate> coordinateSet) {
        SearchRecord searchRecord = new SearchRecord();
        searchRecord.setLatitude(search.getLatitude());
        searchRecord.setLongitude(search.getLongitude());
        searchRecord.setRadius(search.getRadius());
        searchRecord.setCoordinateList(coordinateSet);

        return searchRecord;
    }

    public void saveSearchRecord(SearchRecord searchRecord) {
        log.info("Search saving to db", searchRecord.getLatitude(), searchRecord.getLongitude(), searchRecord.getRadius());
        searchRecordRepository.save(searchRecord);
    }
}
