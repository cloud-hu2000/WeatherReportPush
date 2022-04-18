package com.CloudHu.weather.listener;
import com.CloudHu.weather.service.impl.AdminServiceImpl;
import com.CloudHu.weather.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class SendWeatherListener implements ServletContextListener {


	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
	}
 
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		TimerManager.sendWeather(23,55,10);

	}
 
}
