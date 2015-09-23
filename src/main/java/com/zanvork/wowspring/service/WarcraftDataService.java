/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zanvork.wowspring.service;

import com.zanvork.wowspring.model.CharacterClass;
import com.zanvork.wowspring.model.CharacterRace;
import com.zanvork.wowspring.model.DAO.CharacterClassHibernateDAO;
import com.zanvork.wowspring.model.DAO.CharacterRaceHibernateDAO;
import com.zanvork.wowspring.model.DAO.RealmHibernateDAO;
import com.zanvork.wowspring.model.Realm;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jgreeman
 */
@Service
public class WarcraftDataService extends BaseService {
    
    @Autowired
    private RealmHibernateDAO realmDAO;
    @Autowired
    private CharacterClassHibernateDAO classDAO;
    @Autowired
    private CharacterRaceHibernateDAO raceDAO;
    
    private Map<Long, CharacterClass>   classes =   new HashMap<>();
    private Map<Long, CharacterRace>    races   =   new HashMap<>();
    private Map<String, Realm>          realms  =   new HashMap<>();
    
    private final Object classesLock    =   new Object();
    private final Object racesLock      =   new Object();
    private final Object realmsLock     =   new Object();

    private static WarcraftDataService instance =   null;
    
    public WarcraftDataService(){
       
    }
    
    public static WarcraftDataService getInstance() {
        if (instance == null){
            instance    =   new WarcraftDataService();
        }
        return instance;
    }

    public CharacterClass getClass(long i){
        synchronized(classesLock){
            if (classes.containsKey(i)){
               return classes.get(i);
            }
        }
        return null;
    }
    
    public CharacterRace getRace(long i){
        synchronized(racesLock){
            if (races.containsKey(i)){
               return races.get(i);
            }
        }
        return null;
    }
    
    public Realm getRealm(String key){
        synchronized(realmsLock){
            if (realms.containsKey(key)){
               return realms.get(key);
            }
        }
        return null;
    }
    
    @Override
    public void runUpdate() {
        loadClasses();
        loadRaces();
        loadRealms();
    }
    
    private void loadClasses(){
        Map<Long, CharacterClass>  newClasses    =   new HashMap<>();
        classDAO.findAll().forEach((clazz) -> newClasses.put(clazz.getId(), clazz));
        synchronized(classesLock){
            classes =   newClasses;
        }

    }

    private void loadRaces(){
        Map<Long, CharacterRace>  newRaces  =   new HashMap<>();
        raceDAO.findAll().forEach((race) -> newRaces.put(race.getId(), race));
        synchronized(racesLock){
            races =   newRaces;
        }
    }

    private void loadRealms(){
        Map<String, Realm>  newRealms   =   new HashMap<>();
        realmDAO.findAll().forEach((realm)-> newRealms.put(realm.getRegion().toString() + "_" + realm.getName(), realm));
        synchronized(realmsLock){
            realms =   newRealms;
        }
    }
    
}
