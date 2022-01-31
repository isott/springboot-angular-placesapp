package com.ericsson.placesapi.controller;

import com.ericsson.placesapi.entity.model.Location;
import com.ericsson.placesapi.entity.model.SearchDTO;
import com.ericsson.placesapi.service.PlacesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apiv1/map")
public class PlacesController {

    private final PlacesService placesService;

    @Autowired
    public PlacesController(PlacesService placesService) {
        this.placesService = placesService;
    }

    @GetMapping("/nearbysearch")
    public List<Location> search(@RequestParam("latitude") float latitude,
                                 @RequestParam("longitude") float longitude,
                                 @RequestParam("radius") float radius) {

        SearchDTO search = SearchDTO.builder().latitude(latitude).longitude(longitude).radius(radius).build();

        return placesService.nearbySearch(search);
    }
}
