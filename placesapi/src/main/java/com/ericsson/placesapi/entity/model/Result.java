package com.ericsson.placesapi.entity.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {

    @JsonProperty("geometry")
    private Geometry geometry;
    @JsonProperty("place_id")
    private String placeId;
}
