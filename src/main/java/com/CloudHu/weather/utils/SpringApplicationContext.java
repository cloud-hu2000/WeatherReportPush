package com.CloudHu.weather.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringApplicationContext implements ApplicationContextAware {

    protected static ApplicationContext context;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        // TODO Auto-generated method stub
        context = (ApplicationContext) applicationContext;
    }
    //通过该方法获取spring中的类
     public static ApplicationContext getContext() {  
            return context;  
     }
}
