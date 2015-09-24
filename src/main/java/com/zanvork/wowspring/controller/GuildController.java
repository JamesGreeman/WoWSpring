/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zanvork.wowspring.controller;

import com.zanvork.wowspring.model.DAO.GuildHibernateDAO;
import com.zanvork.wowspring.model.DAO.RealmHibernateDAO;
import com.zanvork.wowspring.model.Guild;
import com.zanvork.wowspring.model.Realm;
import com.zanvork.wowspring.model.enums.Regions;
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
@RequestMapping("/guilds")
public class GuildController {
    
    @Autowired
    private GuildHibernateDAO guildDAO;
    @Autowired
    private RealmHibernateDAO realmDAO;
    
    private final WarcraftAPIService parser    =   new WarcraftAPIService();
    
    @RequestMapping("/get/{region}/{realmName}/{name}")
    public Guild getGuild(@PathVariable String region, @PathVariable String realmName, @PathVariable String name){
        Realm realm =   realmDAO.findByRegionAndName(Regions.valueOf(region.toUpperCase()), realmName);
        Guild guild =   guildDAO.findByRealmAndName(realm, name);
        if (guild == null || guild.getId() < 1){
            guild   =   new Guild(parser.getGuild(region, realmName, name), realm);
            guildDAO.save(guild);
        }
        return guild;
    }
    
    @RequestMapping("/update/{region}/{realmName}/{name}")
    public void updateGuild(@PathVariable String region, @PathVariable String realmName, @PathVariable String name){
        Realm realm =   realmDAO.findByRegionAndName(Regions.valueOf(region.toUpperCase()), realmName);
        Guild guild =   guildDAO.findByRealmAndName(realm, name);
        if (guild != null && guild.getId() > 0){
            guild.updateFromREST(parser.getGuild(region, realmName, name));
            guildDAO.save(guild);
        }
    }
}
