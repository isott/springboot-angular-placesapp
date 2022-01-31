package com.ericsson.placesapi.entity.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@Builder
@ToString
public class SearchDTO {
    private float latitude;
    private float longitude;
    private float radius;
}
