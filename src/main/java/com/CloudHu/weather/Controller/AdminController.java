package com.CloudHu.weather.Controller;

import com.CloudHu.weather.dto.Result;
import com.CloudHu.weather.entity.UserWeather;
import com.CloudHu.weather.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
public class AdminController {
    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/addUserWeather", method = RequestMethod.POST , produces = {
            "application/json; charset=utf-8"})
    @ResponseBody
    private Result addUserWeather(@RequestBody Map<String, String> map
    ){
        Result <Integer> result = new Result<>(false,"nullResult");
        try {
            result = adminService.addUserWeather(map);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(result);
        return result;
    }
    @RequestMapping(value = "/getAllUserWeather", method = RequestMethod.POST)
    @ResponseBody
    private List<UserWeather> getAllUserWeather(){
        List <UserWeather> list=adminService.getAllUserWeather();
        for (UserWeather user : list) {
            System.out.println(user);
        }
        return list;
    }

    @RequestMapping(value = "/changePassword", method = RequestMethod.POST)
    @ResponseBody
    private List<UserWeather> changePassword(@RequestBody Map<String, String> map){
        List <UserWeather> list=adminService.getAllUserWeather();
        for (UserWeather user : list) {
            System.out.println(user);
        }
        return list;
    }

    @RequestMapping(value = "/TimerTask", method = RequestMethod.POST)
    private void TimerTask(){
        System.out.println("我被执行了");
    }

}
