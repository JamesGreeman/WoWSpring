/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zanvork.wowspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 *
 * @author jgreeman
 */
@SpringBootApplication
@EnableScheduling
public class ApplicationLauncher {
     
    
    public static void main(String args[]) {
        SpringApplication.run(ApplicationLauncher.class);
    }
    
}
