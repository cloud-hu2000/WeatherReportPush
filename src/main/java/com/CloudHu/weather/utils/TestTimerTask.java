package com.CloudHu.weather.utils;

import com.CloudHu.weather.service.AdminService;
import com.CloudHu.weather.service.impl.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimerTask;
 
/**
 * @author deerw13
 *
 */
public class TestTimerTask extends TimerTask {
	@Override
	public void run() {
		try {
			AdminService adminService = SpringApplicationContext.getContext().getBean(AdminService.class);
			adminService.sendWeather();
		} catch (IOException e) {
			System.out.println("jsoup不能获取doc对象");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
 
}
