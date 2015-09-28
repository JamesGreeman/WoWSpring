package com.zanvork.wowspring.model;

import com.zanvork.wowspring.model.enums.Regions;
import com.zanvork.wowspring.model.rest.RestRealm;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.Data;

/**
 *
 * @author jgreeman
 */
@Data
@Entity
@Table(uniqueConstraints=
           @UniqueConstraint(columnNames = {"slug", "region"})) 
public class Realm implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private String name;
    private String slug;
    @Enumerated(EnumType.STRING)
    private Regions region;
    private String battlegroup;
    private String type;
    private String population;
    private String locale;
    private String timezone;
    
    public Realm(){
        
    }
    
}
