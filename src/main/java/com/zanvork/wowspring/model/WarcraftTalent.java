/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zanvork.wowspring.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
public class WarcraftTalent implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private int tier;
    @JsonProperty(value="column")
    private int talentColumn;
    @OneToOne
    @Cascade({CascadeType.ALL})
    private WarcraftSpell spell;
    @ManyToOne
    @Cascade({CascadeType.ALL})
    private WarcraftCharacterTalents warcraftCharacterTalents;
}
