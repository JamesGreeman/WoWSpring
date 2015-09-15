package com.zanvork.wowspring.model.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RestCharacterItems{
    private int averageItemLevel;
    private int averageItemLevelEquipped;
    
    private RestItem head;
    private RestItem neck;
    private RestItem shoulder;
    private RestItem back;
    private RestItem chest;
    private RestItem shirt;
    private RestItem wrist;
    private RestItem hands;
    private RestItem waist;
    private RestItem legs;
    private RestItem feet;
    private RestItem finger1;
    private RestItem finger2;
    private RestItem trinket1;
    private RestItem trinket2;
    private RestItem mainHand;
    private RestItem offHand;
}
