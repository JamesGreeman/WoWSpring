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
import lombok.Data;

/**
 *
 * @author jgreeman
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class WarcraftTalentSpec implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long    id;
    private String  name;
    @JsonProperty(value="role")
    private String  specRole;
    private String  backgroundImage;
    private String  icon;
    private String  description;
    @JsonProperty(value="order")
    private int     specOrder;
}
