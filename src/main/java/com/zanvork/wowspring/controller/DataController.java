/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zanvork.wowspring.controller;

import com.zanvork.wowspring.model.ToonClass;
import com.zanvork.wowspring.model.ToonRace;
import com.zanvork.wowspring.model.DAO.CharacterClassHibernateDAO;
import com.zanvork.wowspring.model.DAO.CharacterRaceHibernateDAO;
import com.zanvork.wowspring.service.WarcraftAPIService;
import com.zanvork.wowspring.model.DAO.RealmHibernateDAO;
import com.zanvork.wowspring.model.Realm;
import com.zanvork.wowspring.model.enums.Regions;
import com.zanvork.wowspring.model.rest.RestClass;
import com.zanvork.wowspring.model.rest.RestRace;
import com.zanvork.wowspring.model.rest.RestRealm;
import com.zanvork.wowspring.service.GuildService;
import com.zanvork.wowspring.service.WarcraftDataService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jgreeman
 */
@RestController
@RequestMapping("/data")
public class DataController {
    
    @Autowired
    private WarcraftDataService dataService;
    
    @Autowired
    private WarcraftAPIService parser;
    
    /**
     * Updates the realm entries in the database
     * @param region region to update realms for 
     */
    @RequestMapping("/realms/update/{region}")
    public void updateRealms(@PathVariable String region){
        Regions realmsRegions       =   Regions.valueOf(region.toUpperCase());
        List<RestRealm> realms      =   parser.getRealms(realmsRegions);
        realms.stream().forEach(realmData -> dataService.addOrUpdateRealm(realmData, region));
    }
    
    
    /**
     * Updates the class entries in the database.
     */
    @RequestMapping("/classes/update")
    public void updateClasses(){
        List<RestClass> classes =   parser.getClasses();
        classes.stream().forEach(classData -> dataService.addOrUpdateToonClass(classData));
    }
    
    /**
     * Updates the race entries in the database.
     */
    @RequestMapping("/races/update")
    public void updateRaces(){
        List<RestRace> races =   parser.getRaces();
        races.stream().forEach(raceData -> dataService.addOrUpdateToonRace(raceData));
    }
}
