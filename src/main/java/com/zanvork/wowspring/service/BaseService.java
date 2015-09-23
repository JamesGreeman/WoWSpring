/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zanvork.wowspring.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author jgreeman
 */
public abstract class BaseService implements Runnable {
    
    protected boolean isAlive = false;
    protected Date lastRun = null;
    protected long lastRunTime = -1;
    protected long runCount = 0;
    protected int errorCount = 0;
    
    private static final List<BaseService> serviceList =   new ArrayList<>();
    protected static int REFRESH_INTERVAL = 5000;
    protected static final int NUM_SERVICE_THREADS = 5;
    protected static final ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(NUM_SERVICE_THREADS);
    
     public static synchronized void startServices() {
         serviceList.add(WarcraftDataService.getInstance());
    }

    public static synchronized void stopServices() {
        serviceList.stream().forEach((service) -> {
            service.stopService();
        });
    }

    public void stopService(){
        isAlive = false;
    }
    public void startService(){
         if (!isAlive) {
            isAlive = true;

            try {
                runUpdate();
            } catch (Exception ep) {
            }

            BaseService.scheduledThreadPool.scheduleWithFixedDelay(this, REFRESH_INTERVAL, REFRESH_INTERVAL, TimeUnit.MILLISECONDS);
        }
    }
    
    @Override
    public void run(){
          if (isAlive) {
            try {
                lastRun =   new Date();
                runUpdate();
                lastRunTime =   new Date().getTime() - lastRun.getTime();
                runCount++;
            } catch (Exception ep) {
                errorCount++;
            }
        }
    }
    
    public abstract void runUpdate();
}
