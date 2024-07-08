package com.sgmtec.Literatura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Records{
    @JsonAlias("results")
    private List<BookRecord> records;

    public List<BookRecord> getRecords() {
        return records;
    }

    public void setRecords(List<BookRecord> records) {
        this.records = records;
    }
}




