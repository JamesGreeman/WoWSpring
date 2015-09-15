package com.zanvork.wowspring.utils;

import com.zanvork.wowspring.model.DAO.RealmHibernateDAO;
import com.zanvork.wowspring.model.enums.Region;
import com.zanvork.wowspring.model.rest.RestCharacter;
import com.zanvork.wowspring.model.rest.RestGuild;
import com.zanvork.wowspring.model.rest.RestRealm;
import com.zanvork.wowspring.model.rest.RestRealms;
import com.zanvork.wowspring.utils.BattleNetRequest;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author jgreeman
 */
public class WarcraftAPIParser {
    
    Logger log  =   LoggerFactory.getLogger(WarcraftAPIParser.class);
    public RestCharacter loadCharacter(String realm, String name){
        RestTemplate restTemplate = new RestTemplate();
        RestCharacter character = restTemplate.getForObject("https://eu.api.battle.net/wow/character/" + realm + "/" + name + "?fields=talents,items&locale=en_GB&apikey=xv7958srfdsk3vsrj7e5gh45r7gy5jrd", RestCharacter.class);
        log.info(character.toString());
        return character;
    }
    
    
    public RestGuild loadGuild(String realm, String name){
        RestTemplate restTemplate = new RestTemplate();
        RestGuild guild = restTemplate.getForObject("https://eu.api.battle.net/wow/guild/" + realm + "/" + name + "?fields=members&locale=en_GB&apikey=xv7958srfdsk3vsrj7e5gh45r7gy5jrd", RestGuild.class);
        log.info(guild.toString());
        guild.getMembers().stream().filter((member) -> (member.getGuildMember().getName().contains("Zanv"))).forEach((member) -> {
            member.setGuildMember(loadCharacter(guild.getRealm(), member.getGuildMember().getName()));
        });
        return guild;
    }
    
    /**
     * Load all realms for a specified region
     * @param region region to be loaded
     * @return list of RestRealm models
     */
    public List<RestRealm> loadRealms(Region region){
        RestTemplate restTemplate   =   new RestTemplate();
        RestRealms realmsWrapper   =   restTemplate.getForObject(BattleNetRequest.buildObjectRequest("realm", region.name()), RestRealms.class);
        
        return realmsWrapper.getRealms();
    }
    
}
