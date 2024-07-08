package com.sgmtec.Literatura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AuthorRecord(
        String name,
       @JsonAlias("birth_year") Integer birthYyear,
       @JsonAlias("death_year") Integer deathYear
) {
}
