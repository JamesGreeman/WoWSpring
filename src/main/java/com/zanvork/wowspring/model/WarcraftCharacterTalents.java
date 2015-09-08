/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zanvork.wowspring.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
public class WarcraftCharacterTalents implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private boolean selected    =   false;
    @OneToMany(mappedBy = "warcraftCharacterTalents")
    @Cascade({CascadeType.ALL})
    private List<WarcraftTalent> talents;
    @OneToOne(mappedBy = "warcraftCharacterTalents")
    @Cascade({CascadeType.ALL})
    private WarcraftGlyphs glyphs;
    @OneToOne
    @Cascade({CascadeType.ALL})
    private WarcraftTalentSpec spec;
    private String calcTalent;
    private String calcSpec;
    private String calcGlyph;
    @ManyToOne
    private WarcraftCharacter warcraftCharacter;
}
