package com.zanvork.wowspring.model;

import com.zanvork.wowspring.model.enums.Region;
import java.io.Serializable;
import javax.persistence.Entity;
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
           @UniqueConstraint(columnNames = {"name", "region"})) 
public class Realm implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private String name;
    private Region region;
    private String battlegroup;
    
    public Realm(){
        this("", Region.EU);
    }
    
    public Realm(String name, Region region){
        this(name, region, "");
    }
    
    public Realm(String name, Region region, String battlegroup){
        this.name           =   name;
        this.region         =   region;
        this.battlegroup    =   battlegroup;
    }
}
