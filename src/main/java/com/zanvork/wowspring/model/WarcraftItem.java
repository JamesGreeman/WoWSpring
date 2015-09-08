/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zanvork.wowspring.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
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
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class WarcraftItem  implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private String name;
    private String icon;
    private int quality;
    private int itemLevel;
    private List<Map<String, Integer>> stats;
    private int armor;
    private String context;
    private List<Integer> bonusLists;
    @OneToOne
    private WarcraftCharacterItems warcraftCharacterItems;
}
