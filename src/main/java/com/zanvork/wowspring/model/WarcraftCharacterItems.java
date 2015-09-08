/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zanvork.wowspring.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class WarcraftCharacterItems implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    
    private int averageItemLevel;
    private int averageItemLevelEquipped;
    
    
    
    @OneToOne
    @Cascade({CascadeType.ALL})
    private WarcraftItem head;
    @OneToOne
    @Cascade({CascadeType.ALL})
    private WarcraftItem neck;
    @OneToOne
    @Cascade({CascadeType.ALL})
    private WarcraftItem shoulder;
    @OneToOne
    @Cascade({CascadeType.ALL})
    private WarcraftItem back;
    @OneToOne
    @Cascade({CascadeType.ALL})
    private WarcraftItem chest;
    @OneToOne
    @Cascade({CascadeType.ALL})
    private WarcraftItem shirt;
    @OneToOne
    @Cascade({CascadeType.ALL})
    private WarcraftItem wrist;
    @OneToOne
    @Cascade({CascadeType.ALL})
    private WarcraftItem hands;
    @OneToOne
    @Cascade({CascadeType.ALL})
    private WarcraftItem waist;
    @OneToOne
    @Cascade({CascadeType.ALL})
    private WarcraftItem legs;
    @OneToOne
    @Cascade({CascadeType.ALL})
    private WarcraftItem feet;
    @OneToOne
    @Cascade({CascadeType.ALL})
    private WarcraftItem finger1;
    @OneToOne
    @Cascade({CascadeType.ALL})
    private WarcraftItem finger2;
    @OneToOne
    @Cascade({CascadeType.ALL})
    private WarcraftItem trinket1;
    @OneToOne
    @Cascade({CascadeType.ALL})
    private WarcraftItem trinket2;
    @OneToOne
    @Cascade({CascadeType.ALL})
    private WarcraftItem mainHand;
    @OneToOne
    @Cascade({CascadeType.ALL})
    private WarcraftItem offHand;
}
