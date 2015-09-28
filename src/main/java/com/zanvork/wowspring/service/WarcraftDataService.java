/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zanvork.wowspring.service;

import com.zanvork.wowspring.model.ToonClass;
import com.zanvork.wowspring.model.ToonRace;
import com.zanvork.wowspring.model.DAO.CharacterClassHibernateDAO;
import com.zanvork.wowspring.model.DAO.CharacterRaceHibernateDAO;
import com.zanvork.wowspring.model.DAO.RealmHibernateDAO;
import com.zanvork.wowspring.model.Realm;
import com.zanvork.wowspring.model.enums.Factions;
import com.zanvork.wowspring.model.enums.Regions;
import com.zanvork.wowspring.model.rest.RestClass;
import com.zanvork.wowspring.model.rest.RestRace;
import com.zanvork.wowspring.model.rest.RestRealm;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 *
 * @author jgreeman
 */
@Service
public class WarcraftDataService implements BackendService{
    
    @Autowired
    private RealmHibernateDAO realmDAO;
    @Autowired
    private CharacterClassHibernateDAO classDAO;
    @Autowired
    private CharacterRaceHibernateDAO raceDAO;
    
    private Map<Long, ToonClass>    toonClasses =   new HashMap<>();
    private Map<Long, ToonRace>     toonRaces   =   new HashMap<>();
    private Map<String, Realm>      realms      =   new HashMap<>();
    
    private final Object classesLock    =   new Object();
    private final Object racesLock      =   new Object();
    private final Object realmsLock     =   new Object();

    
    public ToonClass getToonClass(long i){
        synchronized(classesLock){
            if (toonClasses.containsKey(i)){
               return toonClasses.get(i);
            }
        }
        return null;
    }
    
    public ToonClass addOrUpdateToonClass(RestClass classData){
        ToonClass toonClass   =   classDAO.findOne(classData.getId());
        
        if (toonClass == null || toonClass.getId() < 0){
            toonClass =   new ToonClass();
            toonClass.setId(classData.getId());
        }
        
        toonClass.setMask(classData.getMask());
        toonClass.setPowerType(classData.getPowerType());
        toonClass.setName(classData.getName());
        
        classDAO.save(toonClass);
        return toonClass;
    }
    
    public ToonRace getToonRace(long i){
        synchronized(racesLock){
            if (toonRaces.containsKey(i)){
               return toonRaces.get(i);
            }
        }
        return null;
    }
    
    public ToonRace addOrUpdateToonRace(RestRace raceData){
        ToonRace toonRace   =   raceDAO.findOne(raceData.getId());
        
        if (toonRace == null || toonRace.getId() < 0){
            toonRace =   new ToonRace();
            toonRace.setId(raceData.getId());
        }
        
        toonRace.setMask(raceData.getMask());
        toonRace.setFaction(Factions.valueOf(raceData.getSide().toUpperCase()));
        toonRace.setName(raceData.getName());
        
        raceDAO.save(toonRace);
        return toonRace;
    }
    public Realm getRealm(String realmName, String region){
        return getRealm(getRealmKey(realmName, Regions.valueOf(region.toUpperCase())));
    }
    public Realm getRealm(String key){
        synchronized(realmsLock){
            if (realms.containsKey(key)){
               return realms.get(key);
            }
        }
        return null;
    }
    public Realm addOrUpdateRealm(RestRealm realmData, String region){
        Regions regionEnum  =   Regions.valueOf(region.toUpperCase());
        Realm realm         =   realmDAO.findByRegionAndSlug(regionEnum, realmData.getSlug());
        
        if (realm == null || realm.getId() < 1){
            realm =   new Realm();
            realm.setName(realmData.getName());
            realm.setSlug(realmData.getSlug());
            realm.setRegion(regionEnum);
        }
        realm.setBattlegroup(realmData.getBattlegroup());
        realm.setType(realmData.getType());
        realm.setPopulation(realmData.getPopulation());
        realm.setLocale(realmData.getLocale());
        realm.setTimezone(realmData.getTimezone());
        
        realmDAO.save(realm);
        return realm;
    }
    
    
    
    
    private String getRealmKey(String realmName, Regions region){
        return region.toString() + "_" + realmName.toLowerCase();
    }
    
    @Scheduled(fixedDelay=5000)
    @Override
    public void runUpdate() {
        loadClasses();
        loadRaces();
        loadRealms();
    }
    
    private void loadClasses(){
        Map<Long, ToonClass>  newClasses    =   new HashMap<>();
        classDAO.findAll().forEach((toonClass) -> newClasses.put(toonClass.getId(), toonClass));
        synchronized(classesLock){
            toonClasses =   newClasses;
        }

    }

    private void loadRaces(){
        Map<Long, ToonRace>  newRaces  =   new HashMap<>();
        raceDAO.findAll().forEach((race) -> newRaces.put(race.getId(), race));
        synchronized(racesLock){
            toonRaces =   newRaces;
        }
    }

    private void loadRealms(){
        Map<String, Realm>  newRealms   =   new HashMap<>();
        realmDAO.findAll().forEach((realm)-> {
            newRealms.put(getRealmKey(realm.getName(), realm.getRegion()), realm);
        });
        synchronized(realmsLock){
            realms =   newRealms;
        }
    }
    
}
