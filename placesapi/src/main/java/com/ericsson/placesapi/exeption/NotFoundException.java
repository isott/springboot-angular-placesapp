package com.ericsson.placesapi.exeption;

import com.ericsson.placesapi.entity.model.SearchDTO;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
@Getter
public class NotFoundException extends RuntimeException {
    private final SearchDTO search;

    public NotFoundException(SearchDTO search) {
        super(String.format("The searched location was not found according to these coordinates. latitude = %s , longitude = %s , radius = %s",
                search.getLatitude(), search.getLongitude(), search.getRadius()));
        this.search = search;
    }
}
