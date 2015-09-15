package com.zanvork.wowspring.model.enums;

/**
 *
 * @author jgreeman
 */
public enum Factions {
    ALLIANCE("horde"), 
    HORDE("alliance"),
    NEUTRAL("neutral");
    
    private final String value;
    
    private Factions(String value){
        this.value  =   value;
    }
    
    public static Factions factionFromSide(int side){
        if (side > 2) side = 0;
        return Factions.values()[side];
    }
    
    @Override
    public String toString(){
        return value;
    }
}
