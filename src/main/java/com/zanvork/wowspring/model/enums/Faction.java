package com.zanvork.wowspring.model.enums;

/**
 *
 * @author jgreeman
 */
public enum Faction {
    ALLIANCE("horde"), 
    HORDE("alliance"),
    NEUTRAL("neutral");
    
    private final String value;
    
    private Faction(String value){
        this.value  =   value;
    }
    
    public static Faction factionFromSide(int side){
        switch (side){
            case 0:
                return ALLIANCE;
            case 1:
                return HORDE;
            default:
               return NEUTRAL;
        }
    }
    
    @Override
    public String toString(){
        return value;
    }
}
