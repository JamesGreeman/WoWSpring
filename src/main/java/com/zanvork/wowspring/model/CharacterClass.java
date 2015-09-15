/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zanvork.wowspring.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;

/**
 *
 * @author jgreeman
 */
@Data
@Entity
public class CharacterClass implements Serializable {
    @Id
    private long id;
    private long mask;
    private String powerType;
    private String name;
}
