/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zanvork.wowspring.model;

import com.zanvork.wowspring.model.enums.Factions;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import lombok.Data;

/**
 *
 * @author jgreeman
 */
@Data
@Entity
public class CharacterRace implements Serializable {
    @Id
    private long id;
    private long mask;
    @Enumerated(EnumType.STRING)
    private Factions faction;
    private String name;
}
