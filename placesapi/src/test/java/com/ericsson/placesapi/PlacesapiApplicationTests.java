package com.ericsson.placesapi;

import static org.assertj.core.api.Assertions.assertThat;

import com.ericsson.placesapi.controller.PlacesController;
import com.ericsson.placesapi.repository.CoordinateRepository;
import com.ericsson.placesapi.repository.SearchRecordRepository;
import com.ericsson.placesapi.service.CoordinateService;
import com.ericsson.placesapi.service.PlacesService;
import com.ericsson.placesapi.service.SearchRecordService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
class PlacesapiApplicationTests {

    @Autowired
    PlacesController placesController;

    @Autowired
    CoordinateService coordinateService;

    @Autowired
    SearchRecordService searchRecordService;

    @Autowired
    PlacesService placesService;

    @Autowired
    CoordinateRepository coordinateRepository;

    @Autowired
    SearchRecordRepository searchRecordRepository;

    @Autowired
    RestTemplate restTemplate;

    @Test
    void contextLoads() {
        assertThat(placesController).isNotNull();
        assertThat(coordinateService).isNotNull();
        assertThat(searchRecordService).isNotNull();
        assertThat(placesService).isNotNull();
        assertThat(coordinateRepository).isNotNull();
        assertThat(searchRecordRepository).isNotNull();
        assertThat(restTemplate).isNotNull();
    }
}
