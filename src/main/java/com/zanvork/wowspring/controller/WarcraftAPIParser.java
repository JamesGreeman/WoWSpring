package com.zanvork.wowspring.controller;

import com.zanvork.wowspring.model.rest.RestCharacter;
import com.zanvork.wowspring.model.rest.RestGuild;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    
}
