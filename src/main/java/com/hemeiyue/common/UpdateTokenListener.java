package com.hemeiyue.common;

import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


import com.hemeiyue.util.WX_Util;

/**
 * Application Lifecycle Listener implementation class UpdateTokenListener
 *
 */
public class UpdateTokenListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public UpdateTokenListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
    	Timer timer = new Timer(true);
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
					WX_Util.updateToekn();
			}
		}, 0, 3600*1000);
    }
	
}
