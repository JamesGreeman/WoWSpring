package com.zanvork.wowspring.model.enums;

/**
 *
 * @author jgreeman
 */
public enum Region {
    EU("eu"), 
    US("us"),
    KR("kr"),
    TW("tw");
    
    private final String value;
    
    private Region(String value){
        this.value  =   value;
    }
    
    @Override
    public String toString(){
        return value;
    }    
}
