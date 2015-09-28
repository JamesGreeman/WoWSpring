/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zanvork.wowspring.service;

import com.zanvork.wowspring.model.Toon;
import com.zanvork.wowspring.model.DAO.ToonHibernateDAO;
import com.zanvork.wowspring.model.Realm;
import com.zanvork.wowspring.model.enums.Genders;
import com.zanvork.wowspring.model.rest.RestCharacter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 *
 * @author jgreeman
 */
@Service
public class ToonService implements BackendService{
    
    @Autowired
    private ToonHibernateDAO toonDAO;
    
    @Autowired
    private WarcraftDataService dataService;
    
    public Toon getToon(String name, String realmName, String region){
        Realm realm =   dataService.getRealm(realmName, region);
        return toonDAO.findByRealmAndName(realm, name);
    }
    
    public Toon addToon(RestCharacter characterData, String realmName, String region){
        Realm realm =   dataService.getRealm(realmName, region);
        Toon toon =   new Toon();
        toon.setName(characterData.getName());
        toon.setRealm(realm);
        
        return updateToon(toon, characterData);
    }
    
    public Toon updateToon(Toon toon, RestCharacter characterData){
        toon.setCharClass(dataService.getToonClass(characterData.getCharClass()));
        toon.setRace(dataService.getToonRace(characterData.getRace()));
        toon.setGender(Genders.genderFromInt(characterData.getGender()));
        toon.setCharLevel(characterData.getCharLevel());
        toon.setAchievementPoints(characterData.getAchievementPoints());
        toon.setThumbnail(characterData.getThumbnail());

        toonDAO.save(toon);
        
        return toon;
    }
    
    @Scheduled(fixedDelay=5000)
    @Override
    public void runUpdate() {
        
    }
    
}
