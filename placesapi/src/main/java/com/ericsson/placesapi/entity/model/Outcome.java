package com.ericsson.placesapi.entity.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Outcome {

    @JsonProperty("next_page_token")
    private String nextPageToken;
    @JsonProperty("results")
    private List<Result> results;
}
