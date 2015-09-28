/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zanvork.wowspring.service;

import com.zanvork.wowspring.model.DAO.GuildHibernateDAO;
import com.zanvork.wowspring.model.Guild;
import com.zanvork.wowspring.model.Realm;
import com.zanvork.wowspring.model.enums.Factions;
import com.zanvork.wowspring.model.rest.RestGuild;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 *
 * @author jgreeman
 */
@Service
public class GuildService implements BackendService{
    
    @Autowired
    private GuildHibernateDAO guildDAO;
    
    @Autowired
    private WarcraftDataService dataService;
    
    public Guild getGuild(String name, String realmName, String region){
        Realm realm =   dataService.getRealm(realmName, region);
        return guildDAO.findByRealmAndName(realm, name);
    }
    
    public Guild addGuild(RestGuild guildData, String realmName, String region){
        Realm realm =   dataService.getRealm(realmName, region);
        Guild guild =   new Guild();
        guild.setName(guildData.getName());
        guild.setRealm(realm);
        
        return updateGuild(guild, guildData);
    }
    
    public Guild updateGuild(Guild guild, RestGuild guildData){
        guild.setGuildLevel(guildData.getGuildLevel());
        guild.setAchievementPoints(guildData.getAchievementPoints());        
        guild.setFaction(Factions.factionFromSide(guildData.getSide()));
        guildDAO.save(guild);
        
        return guild;
    }
    
    @Scheduled(fixedDelay=5000)
    @Override
    public void runUpdate() {
        
    }
    
}
