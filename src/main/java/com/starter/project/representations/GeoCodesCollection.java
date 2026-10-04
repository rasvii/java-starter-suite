package com.starter.project.representations;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class GeoCodesCollection {

    private List<GeoCodesResponse> results;

    public List<GeoCodesResponse> getResults() {
        return results;
    }

    public void setResults(List<GeoCodesResponse> results) {
        this.results = results;
    }
}
