package com.ericsson.placesapi.controller;

import com.ericsson.placesapi.entity.model.Location;
import com.ericsson.placesapi.service.PlacesService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

class PlacesControllerTest {

    @Mock
    PlacesService placesService;

    @InjectMocks
    PlacesController placesController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSearch() {
        when(placesService.nearbySearch(any())).thenReturn(Arrays.<Location>asList(new Location(Float.valueOf(1.1f), Float.valueOf(1.1f))));

        List<Location> result = placesController.search(0f, 0f, 0f);
        Assertions.assertEquals(Arrays.<Location>asList(new Location(Float.valueOf(1.1f), Float.valueOf(1.1f))), result);
    }
}
