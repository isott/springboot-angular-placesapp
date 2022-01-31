package com.ericsson.placesapi.exeption.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
@Builder
public class ExceptionMessage {
    private String message;
    private String details;
    private Date time;
}
