package com.ericsson.placesapi.service;

import com.ericsson.placesapi.entity.Coordinate;
import com.ericsson.placesapi.entity.SearchRecord;
import com.ericsson.placesapi.model.Location;
import com.ericsson.placesapi.model.Outcome;
import com.ericsson.placesapi.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PlacesService {
    private static final String BASE_API = "https://maps.googleapis.com/maps/api/place";
    private static final String NEARBY_SEARCH = "/nearbysearch";
    private static final String OUT_JSON = "/json";

    // KEY!
    private static final String API_KEY = "AIzaSyBKLinya4d-da_FlDQRXEdRjNI4PHVcCAA";

    private final RestTemplate restTemplate;
    private final CoordinateService coordinateService;
    private final SearchRecordService searchRecordService;

    @Autowired
    public PlacesService(RestTemplate restTemplate,
                         CoordinateService coordinateService,
                         SearchRecordService searchRecordService) {
        this.restTemplate = restTemplate;
        this.coordinateService = coordinateService;
        this.searchRecordService = searchRecordService;
    }

    public String urlBilder(String lat, String lng, String radius) {
        StringBuilder sb = new StringBuilder(BASE_API);
        sb.append(NEARBY_SEARCH);
        sb.append(OUT_JSON + "?");
        sb.append("&location=" + lat + "," + lng);
        sb.append("&radius=" + radius);
        sb.append("&key=" + API_KEY);

        return sb.toString();
    }

    public Outcome paginate(String url, String pageToken) {
        if (!pageToken.isEmpty()) {
            url += "&pagetoken=" + pageToken;
        }
        return restTemplate.getForObject(url, Outcome.class);
    }

    public List<Result> nearBySearch(String url) {
        Outcome response = paginate(url, "");
        List<Result> list = response.getResults();

        while (response.getNextPageToken() != null) {
            response = paginate(url, response.getNextPageToken());
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list.addAll(response.getResults());
        }

        return list;
    }

    public Set<Coordinate> getUnsavedCoordinates(Set<Coordinate> coordinateSet) {
        return coordinateSet.stream().filter(e -> !coordinateService.isCoordinateAlreadyExist(e.getLatitude(), e.getLongitude())).collect(Collectors.toSet());
    }

    public List<Location> coordinatesToLocations(Set<Coordinate> coordinates) {
        return coordinates.stream().map(e -> new Location(e.getLatitude(), e.getLongitude())).collect(Collectors.toList());
    }

    public List<Location> search(String latitude, String longitude, String radius) {

        Optional<SearchRecord> records = searchRecordService.isSearchedBefore(latitude, longitude, radius);
        if (records.isPresent()) {
            return coordinatesToLocations(records.get().getCoordinateList());
        }

        String url = urlBilder(latitude, longitude, radius);
        List<Result> resultList = nearBySearch(url);

        Set<Coordinate> coordinateSet = coordinateService.getCoordinatesFromOutcome(resultList);

        SearchRecord searchRecord = searchRecordService.buildSearchRecord(latitude, longitude, radius, getUnsavedCoordinates(coordinateSet));
        searchRecordService.saveSearchRecord(searchRecord);

        return coordinatesToLocations(coordinateSet);
    }
}
