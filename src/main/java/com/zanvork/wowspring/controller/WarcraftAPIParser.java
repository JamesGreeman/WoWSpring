/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zanvork.wowspring.controller;

import com.zanvork.wowspring.model.DAO.WarcraftGuildHibernateDAO;
import com.zanvork.wowspring.model.WarcraftCharacter;
import com.zanvork.wowspring.model.WarcraftGuild;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author jgreeman
 */
public class WarcraftAPIParser {
    
    Logger log  =   LoggerFactory.getLogger(WarcraftAPIParser.class);
    public WarcraftCharacter loadCharacter(String realm, String name){
        RestTemplate restTemplate = new RestTemplate();
        WarcraftCharacter character = restTemplate.getForObject("https://eu.api.battle.net/wow/character/" + realm + "/" + name + "?fields=talents,items&locale=en_GB&apikey=xv7958srfdsk3vsrj7e5gh45r7gy5jrd", WarcraftCharacter.class);
        log.info(character.toString());
        return character;
    }
    
    
    public WarcraftGuild loadGuild(String realm, String name){
        RestTemplate restTemplate = new RestTemplate();
        WarcraftGuild guild = restTemplate.getForObject("https://eu.api.battle.net/wow/guild/" + realm + "/" + name + "?fields=members&locale=en_GB&apikey=xv7958srfdsk3vsrj7e5gh45r7gy5jrd", WarcraftGuild.class);
        log.info(guild.toString());
        guild.getMembers().stream().filter((member) -> (member.getGuildMember().getName().contains("Zanv"))).forEach((member) -> {
            member.setGuildMember(loadCharacter(guild.getRealm(), member.getGuildMember().getName()));
        });
        WarcraftGuildHibernateDAO dao =   new WarcraftGuildHibernateDAO();
        dao.save(guild);
        
        return guild;
    }
    
}
