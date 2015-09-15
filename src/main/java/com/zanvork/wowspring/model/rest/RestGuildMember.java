package com.zanvork.wowspring.model.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RestGuildMember {
    @JsonProperty(value="character")
    RestCharacter guildMember;
    int rank;

}
