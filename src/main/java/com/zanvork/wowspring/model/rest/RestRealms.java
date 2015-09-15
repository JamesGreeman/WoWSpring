package com.zanvork.wowspring.model.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RestRealms {
    private List<RestRealm> realms;
}
