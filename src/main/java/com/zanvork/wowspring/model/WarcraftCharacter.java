/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zanvork.wowspring.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.Data;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 *
 * @author jgreeman
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class WarcraftCharacter implements Serializable{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long    id;
    private long    lastModified;
    private String  name;
    private String  realm;
    private String  battlegroup;
    
    @JsonProperty(value="class")
    private int     charClass;
    private int     race;
    private int     gender;
    @JsonProperty(value="level")
    private int     charLevel;
    private int     achievementPoints;
    private String  thumbnail;
    private String  calcClass;
    @OneToOne
    @Cascade({CascadeType.ALL})
    private WarcraftCharacterItems items;
    @OneToMany(mappedBy = "warcraftCharacter")
    @Cascade({CascadeType.ALL})
    private List<WarcraftCharacterTalents> talents;
    private long    totalHonorableKills;
    @OneToOne(mappedBy = "guildMember")
    private WarcraftGuildMember warcraftGuildMember;
    
}
