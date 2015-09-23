/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zanvork.wowspring.model;

import com.zanvork.wowspring.model.enums.Genders;
import com.zanvork.wowspring.model.enums.Regions;
import com.zanvork.wowspring.model.rest.RestCharacter;
import com.zanvork.wowspring.service.WarcraftDataService;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.Data;

/**
 *
 * @author jgreeman
 */
@Data
@Entity
@Table(uniqueConstraints=
           @UniqueConstraint(columnNames = {"name", "realm"})) 
public class Character implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private String name;
    @ManyToOne
    private Realm  realm;
    @ManyToOne
    private CharacterClass charClass;
    @ManyToOne
    private CharacterRace race;
    @Enumerated(EnumType.STRING)
    private Genders gender;
    private int charLevel;
    private int achievementPoints;
    private String thumbnail;
    
    public Character(RestCharacter character, Regions region){
        name                =   character.getName();
        realm               =   WarcraftDataService.getInstance().getRealm(region.toString() + "_" + character.getRealm());
        charClass           =   WarcraftDataService.getInstance().getClass(character.getCharClass());
        race                =   WarcraftDataService.getInstance().getRace(character.getRace());
        gender              =   Genders.genderFromInt(character.getGender());
        charLevel           =   character.getCharLevel();
        achievementPoints   =   character.getAchievementPoints();
        thumbnail           =   character.getThumbnail();
    }
    
    public void updateFromREST(RestCharacter character){
        realm               =   WarcraftDataService.getInstance().getRealm(realm.getRegion().toString() + "_" + character.getRealm());
        charClass           =   WarcraftDataService.getInstance().getClass(character.getCharClass());
        race                =   WarcraftDataService.getInstance().getRace(character.getRace());
        gender              =   Genders.genderFromInt(character.getGender());
        charLevel           =   character.getCharLevel();
        achievementPoints   =   character.getAchievementPoints();
        thumbnail           =   character.getThumbnail();
    }
}

