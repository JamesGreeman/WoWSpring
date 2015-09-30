/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zanvork.wowspring.model;

import com.zanvork.wowspring.model.enums.ToonItemSlots;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.Data;

/**
 *
 * @author jgreeman
 */
@Data
@Entity
public class ToonItem implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    
    @Enumerated(EnumType.STRING)
    private ToonItemSlots itemSlot;
    @ManyToOne
    private Item item;
    @ManyToOne
    private Item transmogItem;
    private int enchant;
    private int[] bonusLists;

    public ToonItem() {
    }
    
}
