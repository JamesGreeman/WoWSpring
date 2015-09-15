package com.zanvork.wowspring.model.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RestStat {
    private int stat;
    private int amount;
}
