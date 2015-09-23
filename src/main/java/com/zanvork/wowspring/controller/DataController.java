/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zanvork.wowspring.controller;

import com.zanvork.wowspring.model.CharacterClass;
import com.zanvork.wowspring.model.CharacterRace;
import com.zanvork.wowspring.model.DAO.CharacterClassHibernateDAO;
import com.zanvork.wowspring.model.DAO.CharacterRaceHibernateDAO;
import com.zanvork.wowspring.utils.WarcraftAPIParser;
import com.zanvork.wowspring.model.DAO.RealmHibernateDAO;
import com.zanvork.wowspring.model.Realm;
import com.zanvork.wowspring.model.enums.Regions;
import com.zanvork.wowspring.model.rest.RestClass;
import com.zanvork.wowspring.model.rest.RestRace;
import com.zanvork.wowspring.model.rest.RestRealm;
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
    private RealmHibernateDAO realmDAO;
    @Autowired
    private CharacterClassHibernateDAO classDAO;
    @Autowired
    private CharacterRaceHibernateDAO raceDAO;
    
    private final WarcraftAPIParser parser    =   new WarcraftAPIParser();
    
    
    /**
     * Updates the realm entries in the database
     * @param region region to update realms for 
     */
    @RequestMapping("/realms/update/{region}")
    public void updateRealms(@PathVariable String region){
        Regions realmsRegions       =   Regions.valueOf(region.toUpperCase());
        List<RestRealm> realms      =   parser.getRealms(realmsRegions);
        realms.stream().map((realm) -> {
            Realm realmToAdd    =   realmDAO.findByRegionAndSlug(realmsRegions, realm.getSlug());
            if (realmToAdd == null || realmToAdd.getId() < 1){
                realmToAdd    =   new Realm(realm, realmsRegions);
            } else {
                realmToAdd.updateFromREST(realm);
            }
            return realmToAdd;
        }).forEach((realmToAdd) -> {
            realmDAO.save(realmToAdd);
        });
    }
    
    
    /**
     * Updates the class entries in the database.
     */
    @RequestMapping("/classes/update")
    public void updateClasses(){
        List<RestClass> classes =   parser.getClasses();
        classes.stream().map((clazz) -> {
            CharacterClass classToAdd   =   classDAO.findOne(clazz.getId());
            if (classToAdd == null || classToAdd.getId() < 1){
                classToAdd    =   new CharacterClass(clazz);
            } else {
                classToAdd.updateFromREST(clazz);
            }
            return classToAdd;
        }).forEach((classToAdd) -> {
            classDAO.save(classToAdd);
        });
    }
    
    /**
     * Updates the race entries in the database.
     */
    @RequestMapping("/races/update")
    public void updateRaces(){
        List<RestRace> races =   parser.getRaces();
        races.stream().map((race) -> {
            CharacterRace raceToAdd   =   raceDAO.findOne(race.getId());
            if (raceToAdd == null || raceToAdd.getId() < 1){
                raceToAdd    =   new CharacterRace(race);
            } else {
                raceToAdd.updateFromREST(race);
            }
            return raceToAdd;
        }).forEach((raceToAdd) -> {
            raceDAO.save(raceToAdd);
        });
    }
}
