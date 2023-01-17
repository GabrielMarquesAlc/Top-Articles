package org.example;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class PaginationData {
    @JsonProperty("page")
    private Integer page;
    @JsonProperty("per_page")
    private Integer perPage;
    @JsonProperty("total")
    private Integer total;
    @JsonProperty("total_pages")
    private Integer totalPages;
    @JsonProperty("data")
    private List<Articles> data = new ArrayList<>();
    ;

    public PaginationData() {
        // for jackson
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public List<Articles> getData() {
        return data;
    }

}
