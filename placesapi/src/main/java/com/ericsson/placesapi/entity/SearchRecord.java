package com.ericsson.placesapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "coordinateList")
public class SearchRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Float latitude;
    private Float longitude;
    private Float radius;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "search_coordinate",
            joinColumns = @JoinColumn(name = "coordinate_id"),
            inverseJoinColumns = @JoinColumn(name = "search_id"))
    private Set<Coordinate> coordinateList = new HashSet<>();
}
