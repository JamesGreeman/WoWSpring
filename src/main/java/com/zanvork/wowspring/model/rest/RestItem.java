package com.zanvork.wowspring.model.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RestItem {
    private long id;
    private String name;
    private String icon;
    private int quality;
    private int itemLevel;
    private List<RestStat> stats;
    private int armor;
    private String context;
    private List<Integer> bonusLists;
}
