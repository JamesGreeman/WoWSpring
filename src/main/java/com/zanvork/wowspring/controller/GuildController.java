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
import com.zanvork.wowspring.model.Toon;
import com.zanvork.wowspring.model.enums.Regions;
import com.zanvork.wowspring.service.GuildService;
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
    private GuildService guildService;
    
    @Autowired
    private WarcraftAPIService parser;
    
    @RequestMapping("/get/{region}/{realmName}/{name}")
    public Guild getGuild(@PathVariable String region, @PathVariable String realmName, @PathVariable String name){
        Guild guild =   guildService.getGuild(name, realmName, region);
        if (guild == null || guild.getId() < 1){
            guild   =   guildService.addGuild(parser.getGuild(region, realmName, name), realmName, region);
        }
        return guild;
    }
    
    @RequestMapping("/update/{region}/{realmName}/{name}")
    public String updateGuild(@PathVariable String region, @PathVariable String realmName, @PathVariable String name){
        Guild guild =   guildService.getGuild(name, realmName, region);
        if (guild != null && guild.getId() > 0){
            guildService.updateGuild(guild, parser.getGuild(region, realmName, name));
        }
        return "Successfully Updated";
    }
}
