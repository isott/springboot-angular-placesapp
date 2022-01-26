package com.ericsson.placesapi.controller;

import com.ericsson.placesapi.model.Location;
import com.ericsson.placesapi.service.PlacesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/apiv1/map")
public class PlacesController {

    private final PlacesService placesService;

    @Autowired
    public PlacesController(PlacesService placesService) {
        this.placesService = placesService;
    }

    @GetMapping("/search")
    public List<Location> search(@RequestParam("latitude") String latitude,
                                 @RequestParam("longitude") String longitude,
                                 @RequestParam("radius") String radius) {
        // "-33.8670522", "151.1957362", "100"
        return placesService.search(latitude, longitude, radius);
    }

}
