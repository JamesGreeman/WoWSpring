/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zanvork.wowspring.controller;

import com.zanvork.wowspring.model.Toon;
import com.zanvork.wowspring.service.ToonService;
import com.zanvork.wowspring.service.WarcraftAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jgreeman
 */
@RestController
@RequestMapping("/characters")
public class ToonController {
    @Autowired
    private ToonService toonService;
    
    @Autowired
    private WarcraftAPIService parser;
    
    @RequestMapping("/get/{region}/{realmName}/{name}")
    public Toon getCharacter(@PathVariable String region, @PathVariable String realmName, @PathVariable String name){
        Toon toon =   toonService.getToon(name, realmName, region);
        if (toon == null || toon.getId() < 1){
            toon   =   toonService.addToon(parser.getCharacter(region, realmName, name), realmName, region);
        }
        return toon;
    }
    
    @RequestMapping("/update/{region}/{realmName}/{name}")
    public void updateCharacter(@PathVariable String region, @PathVariable String realmName, @PathVariable String name){
        Toon toon =   toonService.getToon(name, realmName, region);
        if (toon != null && toon.getId() > 0){
            toonService.updateToon(toon, parser.getCharacter(region, realmName, name));
        }
    }
}
