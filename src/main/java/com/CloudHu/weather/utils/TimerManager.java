package com.CloudHu.weather.utils;

import com.CloudHu.weather.listener.SendWeatherListener;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
@Slf4j
public class TimerManager {
    //时间间隔
    private static final long PERIOD_DAY =  24 * 60 * 60 * 1000;

    public static void sendWeather(int hour,int minute,int second) {
    	 Calendar calendar = Calendar.getInstance();

         /*** 定制每日执行方法的时间 ***/
         calendar.set(Calendar.HOUR_OF_DAY, hour);
         calendar.set(Calendar.MINUTE, minute);
         calendar.set(Calendar.SECOND, second);

         Date date=calendar.getTime(); //第一次执行定时任务的时间
         //如果第一次执行定时任务的时间 小于 当前的时间
         //此时要在 第一次执行定时任务的时间 加一天，以便此任务在下个时间点执行。如果不加一天，任务会立即执行。循环执行的周期则以当前时间为准
         if (date.before(new Date())) {
             date = addDay(date, 1);
             System.out.println(date);
         }

         Timer timer = new Timer();

         TestTimerTask task = new TestTimerTask();
         //安排指定的任务在指定的时间开始进行重复的固定延迟执行。
         timer.schedule(task,date,PERIOD_DAY);
         log.info("定时发送天气");
         }

    	// 增加或减少天数
 		public static Date addDay(Date date, int num) {
 		 Calendar startDT = Calendar.getInstance();
 		 startDT.setTime(date);
 		 startDT.add(Calendar.DAY_OF_MONTH, num);
 		 return startDT.getTime();
 		}

}
