/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zanvork.wowspring.model;

import com.zanvork.wowspring.model.enums.ItemStatTypes;
import java.io.Serializable;
import java.util.Map;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import lombok.Data;

/**
 *
 * @author jgreeman
 */
@Data
@Entity
public class Item implements Serializable {
    @Id
    private long id;
    private String name;
    private String icon;
    private String description; 
    
    //Don't store item stats yet
    @Transient
    private Map<ItemStatTypes, Integer> stats;

    public Item() {
    }
}
