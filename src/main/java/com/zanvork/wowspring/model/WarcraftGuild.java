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
public class WarcraftGuild implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long    lastModified;
    private String  name;
    private String  realm;
    private String  battlegroup;
    @JsonProperty(value="level")
    private int     guildLevel;
    private int     side;
    private int     achievmentPoints;
    @OneToMany(mappedBy = "guild")
    @Cascade({CascadeType.ALL})
    private List<WarcraftGuildMember> members;
        
}
