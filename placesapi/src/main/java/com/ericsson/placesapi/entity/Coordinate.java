package com.ericsson.placesapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "searchRecords")
public class Coordinate {

    @Id
    @Column(name = "place_id")
    private String placeId;

    private String latitude;
    private String longitude;


    @ManyToMany(mappedBy = "coordinateList")
    private Set<SearchRecord> searchRecords = new HashSet<>();
}
