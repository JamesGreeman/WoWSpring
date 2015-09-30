/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zanvork.wowspring.service;

import com.zanvork.wowspring.model.Toon;
import com.zanvork.wowspring.model.DAO.ToonHibernateDAO;
import com.zanvork.wowspring.model.Item;
import com.zanvork.wowspring.model.Realm;
import com.zanvork.wowspring.model.ToonItem;
import com.zanvork.wowspring.model.ToonItemSet;
import com.zanvork.wowspring.model.enums.Genders;
import com.zanvork.wowspring.model.rest.RestCharacter;
import com.zanvork.wowspring.model.rest.RestCharacterItems;
import com.zanvork.wowspring.model.rest.RestItem;
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
        
        if (characterData.getItems() != null){
            toon.setItems(updateToonItemSet(toon.getItems(), characterData.getItems()));
        }

        toonDAO.save(toon);
        
        return toon;
    }
    
    public ToonItemSet updateToonItemSet(ToonItemSet itemSet, RestCharacterItems characterItemData){
        if (itemSet == null || itemSet.getId() < 1){
            itemSet =   new ToonItemSet();
        }
        //EWWWWWWWW
        itemSet.setHead(updateToonItem(itemSet.getHead(), characterItemData.getHead()));
        itemSet.setNeck(updateToonItem(itemSet.getNeck(), characterItemData.getNeck()));
        itemSet.setShoulder(updateToonItem(itemSet.getShoulder(), characterItemData.getShoulder()));
        itemSet.setBack(updateToonItem(itemSet.getBack(), characterItemData.getBack()));
        itemSet.setChest(updateToonItem(itemSet.getChest(), characterItemData.getChest()));
        itemSet.setShirt(updateToonItem(itemSet.getShirt(), characterItemData.getShirt()));
        itemSet.setWrist(updateToonItem(itemSet.getWrist(), characterItemData.getWrist()));
        itemSet.setHands(updateToonItem(itemSet.getHands(), characterItemData.getHands()));
        itemSet.setWaist(updateToonItem(itemSet.getWaist(), characterItemData.getWaist()));
        itemSet.setLegs(updateToonItem(itemSet.getLegs(), characterItemData.getLegs()));
        itemSet.setFeet(updateToonItem(itemSet.getFeet(), characterItemData.getFeet()));
        itemSet.setFinger1(updateToonItem(itemSet.getFinger1(), characterItemData.getFinger1()));
        itemSet.setFinger2(updateToonItem(itemSet.getFinger2(), characterItemData.getFinger2()));
        itemSet.setTrinket1(updateToonItem(itemSet.getTrinket1(), characterItemData.getTrinket1()));
        itemSet.setTrinket2(updateToonItem(itemSet.getTrinket2(), characterItemData.getTrinket2()));
        itemSet.setMainHand(updateToonItem(itemSet.getMainHand(), characterItemData.getMainHand()));
        itemSet.setOffHand(updateToonItem(itemSet.getOffHand(), characterItemData.getOffHand()));
        
        itemSet.setAverageItemLevel(characterItemData.getAverageItemLevel());
        
        return itemSet;
    }
    
    public ToonItem updateToonItem(ToonItem toonItem, RestItem itemData){
        if (itemData != null){
            if (toonItem == null || toonItem.getId() < 1){
                toonItem    =   new ToonItem();
            }
            Item item   =   dataService.getItem(itemData.getId());
            if (item == null){
                item    =   dataService.addOrUpdateItem(itemData);
            }
            toonItem.setItem(item);
            
        } else {
            toonItem    =   null;
        }
        return toonItem;
    }
    
    @Scheduled(fixedDelay=5000)
    @Override
    public void runUpdate() {
        
    }
    
}
