package com.ericsson.placesapi.client;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

class PlacesClientTest {

    @Mock
    RestTemplate restTemplate;

    @InjectMocks
    PlacesClient placesClient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testUrlBilder() {
        String result = placesClient.urlBilder(0f, 0f, 0f);
        Assertions.assertEquals("https://maps.googleapis.com/maps/api/place/nearbysearch/json?&location=0.0,0.0&radius=0.0&key=null", result);
    }
}
