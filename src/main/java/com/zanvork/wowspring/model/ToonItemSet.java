/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zanvork.wowspring.model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.Data;

/**
 *
 * @author jgreeman
 */
@Data
@Entity
public class ToonItemSet implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    
    private long id;
    @OneToOne(cascade=CascadeType.ALL)
    private ToonItem head;
    @OneToOne(cascade=CascadeType.ALL)
    private ToonItem neck;
    @OneToOne(cascade=CascadeType.ALL)
    private ToonItem shoulder;
    @OneToOne(cascade=CascadeType.ALL)
    private ToonItem back;
    @OneToOne(cascade=CascadeType.ALL)
    private ToonItem chest;
    @OneToOne(cascade=CascadeType.ALL)
    private ToonItem shirt;
    @OneToOne(cascade=CascadeType.ALL)
    private ToonItem wrist;
    @OneToOne(cascade=CascadeType.ALL)
    private ToonItem hands;
    @OneToOne(cascade=CascadeType.ALL)
    private ToonItem waist;
    @OneToOne(cascade=CascadeType.ALL)
    private ToonItem legs;
    @OneToOne(cascade=CascadeType.ALL)
    private ToonItem feet;
    @OneToOne(cascade=CascadeType.ALL)
    private ToonItem finger1;
    @OneToOne(cascade=CascadeType.ALL)
    private ToonItem finger2;
    @OneToOne(cascade=CascadeType.ALL)
    private ToonItem trinket1;
    @OneToOne(cascade=CascadeType.ALL)
    private ToonItem trinket2;
    @OneToOne(cascade=CascadeType.ALL)
    private ToonItem mainHand;
    @OneToOne(cascade=CascadeType.ALL)
    private ToonItem offHand;
    
    private int averageItemLevel;

    public ToonItemSet() {
    }
    
}
