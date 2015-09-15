package com.zanvork.wowspring.model.enums;

/**
 *
 * @author jgreeman
 */
public enum Regions {
    EU("eu"), 
    US("us"),
    KR("kr"),
    TW("tw");
    
    private final String value;
    
    private Regions(String value){
        this.value  =   value;
    }
    
    @Override
    public String toString(){
        return value;
    }    
}
