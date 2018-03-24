package ru.martha.lab2_1createmcs.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Film {
    private Long id;
    private String name;
    private String ageRestriction;
    private Integer duration;
    private String country;
    private Double imdb;
}
