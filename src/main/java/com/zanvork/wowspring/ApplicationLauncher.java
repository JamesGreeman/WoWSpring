/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zanvork.wowspring;

import com.zanvork.wowspring.controller.WarcraftAPIParser;
import com.zanvork.wowspring.model.DAO.GuildHibernateDAO;
import com.zanvork.wowspring.model.Guild;
import com.zanvork.wowspring.model.enums.Region;
import com.zanvork.wowspring.model.rest.RestGuild;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jgreeman
 */
@SpringBootApplication
public class ApplicationLauncher implements CommandLineRunner {
     @Autowired
    private GuildHibernateDAO hibernateDAO;

    @Override
    @Transactional
    public void run(String... strings) throws Exception {
        WarcraftAPIParser   parser      =   new WarcraftAPIParser();
        RestGuild       guild       =   parser.loadGuild("Quel'Thalas", "Lore");
        Guild guildToSave   =   new Guild(guild, Region.EU);
        hibernateDAO.save(guildToSave);
    }
    
    public static void main(String args[]) {
        SpringApplication.run(ApplicationLauncher.class);
    }
    
}
