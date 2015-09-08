/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zanvork.wowspring;

import com.zanvork.wowspring.controller.WarcraftAPIParser;
import com.zanvork.wowspring.model.WarcraftGuild;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * @author jgreeman
 */
@SpringBootApplication
public class ApplicationLauncher implements CommandLineRunner {

    @Override
    public void run(String... strings) throws Exception {
        WarcraftAPIParser   parser      =   new WarcraftAPIParser();
        WarcraftGuild       guild       =   parser.loadGuild("Quel'Thalas", "Lore");
    }
    
    public static void main(String args[]) {
        SpringApplication.run(ApplicationLauncher.class);
    }
    
}
