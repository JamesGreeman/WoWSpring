package com.zanvork.wowspring.utils;

import com.zanvork.wowspring.model.enums.Regions;
import com.zanvork.wowspring.model.rest.RestCharacter;
import com.zanvork.wowspring.model.rest.RestGuild;
import com.zanvork.wowspring.model.rest.RestRealm;
import com.zanvork.wowspring.model.rest.RestRealms;
import java.util.List;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author jgreeman
 */
public class WarcraftAPIParser {
    
    
    /**
     * Load information for a specified character
     * @param region region the character is in
     * @param realm realm the character is in
     * @param name name of the character
     * @return the character object
     */
    public RestCharacter loadCharacter(Regions region, String realm, String name){
        return new RestTemplate().getForObject(
                BattleNetRequest.buildRequest("character", region.toString(), realm, name, new String[]{"talents","items"}),
                RestCharacter.class
        );
    }
    
    public RestGuild getGuild(Regions region, String realm, String name){
        return WarcraftAPIParser.this.getGuild(region, realm, name, false);
    }
    /**
     * Load information for a specified guild
     * @param region region the guild is in
     * @param realm realm the guild is on
     * @param name name of the guild
     * @param getMemberDetails whether to load details of all guild members
     * @return the guild object
     */
    public RestGuild getGuild(Regions region, String realm, String name, boolean getMemberDetails){
        RestGuild guild = new RestTemplate().getForObject(
                BattleNetRequest.buildRequest("guild", region.name(), realm, name, new String[]{"members"}),
                RestGuild.class
        );
        if (getMemberDetails){
            guild.getMembers().stream().forEach((member) -> {
                member.setGuildMember(loadCharacter(region, guild.getRealm(), member.getGuildMember().getName()));
            });
        }
        return guild;
    }
    
    /**
     * Load all realms for a specified region
     * @param region region to be loaded
     * @return list of RestRealm models
     */
    public List<RestRealm> getRealms(Regions region){
        return new RestTemplate().getForObject(
                BattleNetRequest.buildObjectRequest("realm", region.name()), 
                RestRealms.class
        ).getRealms();
    }    
}
