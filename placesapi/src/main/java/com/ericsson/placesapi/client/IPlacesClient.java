package com.ericsson.placesapi.client;

import com.ericsson.placesapi.entity.model.Result;
import com.ericsson.placesapi.entity.model.SearchDTO;

import java.util.List;

public interface IPlacesClient {
    public List<Result> nearBySearch(SearchDTO search);
}
