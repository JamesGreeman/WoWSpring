package com.zanvork.wowspring.model;

import com.zanvork.wowspring.model.enums.Factions;
import com.zanvork.wowspring.model.enums.Regions;
import com.zanvork.wowspring.model.rest.RestGuild;
import java.io.Serializable;
import javax.persistence.CascadeType;
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
 * Database storage model for guilds
 * @author jgreeman
 */
@Data
@Entity
@Table(uniqueConstraints=
           @UniqueConstraint(columnNames = {"name", "realm"})) 
public class Guild implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    
    private String  name;
    @ManyToOne
    private Realm   realm;
    
    private int     guildLevel;
    private int     achievementPoints;
    @Enumerated(EnumType.STRING)
    private Factions faction;
    
    public Guild(){
        
    }
    
}
