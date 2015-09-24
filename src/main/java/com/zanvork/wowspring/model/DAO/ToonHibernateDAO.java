/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zanvork.wowspring.model.DAO;

import com.zanvork.wowspring.model.Toon;
import com.zanvork.wowspring.model.Realm;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author jgreeman
 */
public interface ToonHibernateDAO extends CrudRepository<Toon, Long>{
    
    public Toon findByRealmAndName(Realm realm, String name);
}
