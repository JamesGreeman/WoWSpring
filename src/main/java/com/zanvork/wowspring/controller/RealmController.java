/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zanvork.wowspring.controller;

import com.zanvork.wowspring.utils.WarcraftAPIParser;
import com.zanvork.wowspring.model.DAO.RealmHibernateDAO;
import com.zanvork.wowspring.model.Realm;
import com.zanvork.wowspring.model.enums.Regions;
import com.zanvork.wowspring.model.rest.RestRealm;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jgreeman
 */
@RestController
@RequestMapping("/realms")
public class RealmController {
    
    @Autowired
    private RealmHibernateDAO hibernateDAO;
    
    
    /**
     * Updates the realm entries in the database
     * @param region region to update realms for 
     */
    @RequestMapping("/update/{region}")
    public void updateRealms(@PathVariable String region){
        WarcraftAPIParser parser    =   new WarcraftAPIParser();
        Regions realmsRegions        =   Regions.valueOf(region.toUpperCase());
        List<RestRealm> realms      =   parser.getRealms(realmsRegions);
        realms.stream().map((realm) -> {
            Realm realmToAdd    =   hibernateDAO.findByRegionAndSlug(realmsRegions, realm.getSlug());
            if (realmToAdd == null || realmToAdd.getId() < 1){
                realmToAdd    =   new Realm(realm, realmsRegions);
            } else {
                realmToAdd.updateRealm(realm);
            }
            return realmToAdd;
        }).forEach((realmToAdd) -> {
            hibernateDAO.save(realmToAdd);
        });
    }
}
