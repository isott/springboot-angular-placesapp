package com.ericsson.placesapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Location {
    @JsonProperty("lat")
    private String latitude;
    @JsonProperty("lng")
    private String longitude;
}
