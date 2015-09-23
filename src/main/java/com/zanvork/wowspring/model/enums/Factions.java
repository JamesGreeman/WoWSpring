package com.zanvork.wowspring.model.enums;

/**
 *
 * @author jgreeman
 */
public enum Factions {
    ALLIANCE, 
    HORDE,
    NEUTRAL;
    
    public static Factions factionFromSide(int side){
        if (side > 2) side = 0;
        return Factions.values()[side];
    }
    
}
