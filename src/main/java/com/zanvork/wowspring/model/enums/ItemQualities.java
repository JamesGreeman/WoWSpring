/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zanvork.wowspring.model.enums;

/**
 *
 * @author jgreeman
 */
public enum ItemQualities {
    POOR,
    COMMON,
    UNCOMMON,
    RARE, 
    EPIC,
    LEGENDARY,
    ARTIFACT,
    HEIRLOOM,
    UNKNOWN;
    
     public static ItemQualities qualityFromInt(int quality){
        if (quality > 8) quality = 8;
        return ItemQualities.values()[quality];
    }
}
