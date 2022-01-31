package com.ericsson.placesapi.entity.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Location {
    @JsonProperty("lat")
    private Float latitude;
    @JsonProperty("lng")
    private Float longitude;
}
