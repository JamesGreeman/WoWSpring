/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zanvork.wowspring.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;

/**
 *
 * @author jgreeman
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class WarcraftSpell implements Serializable {
    @Id
    private long id;
    private String name;
    private String icon;
    private String description;
    private String range;
    private String powerCost;
    private String castTime;
    private String cooldown;
}
