/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zanvork.wowspring.utils;

import java.util.List;

/**
 *
 * @author jgreeman
 */
public class BattleNetRequest {
    private static final String LOCALE  =   "en_GB";
    private static final String API_KEY =   "xv7958srfdsk3vsrj7e5gh45r7gy5jrd";
    
    public static String buildObjectRequest(String object){
        return buildObjectRequest(object, "eu");
    }
    public static String buildObjectRequest(String object, String region){
        return buildObjectRequest(object, region, "");
    }
    public static String buildObjectRequest(String object, String region, String realm){
        String name =   "";
        if ("realm".equals(object)){
            name = "status";
        }
        return buildObjectRequest(object, region, realm, name);
    }
    public static String buildObjectRequest(String object, String region, String realm, String name){
        return buildObjectRequest(object, region, realm, name, null);
    }
    
    public static String buildObjectRequest(String object, String region, String realm, String name, List<String> fields){
        StringBuilder requestBuilder    =   new StringBuilder();
        requestBuilder.append("https://")
                .append(region.toLowerCase())
                .append(".api.battle.net/wow/")
                .append(object)
                .append("/")
                .append(name)
                .append("?");
        if (fields != null && !fields.isEmpty()){
            requestBuilder.append("fields=");
            String seperator    =   "";
            for (String field : fields){
                requestBuilder.append(seperator)
                        .append(field);
                seperator = ",";
            }
            requestBuilder.append("&");
        }
        requestBuilder.append("locale=")
                .append(LOCALE)
                .append("&apikey=")
                .append(API_KEY);
        
        return requestBuilder.toString();
    }
    
}