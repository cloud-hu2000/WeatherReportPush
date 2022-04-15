package com.CloudHu.weather.Controller;

import com.CloudHu.weather.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    private UserService weatherService;

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    @ResponseBody
    private int addUser(@RequestBody Map<String, String> map
    ){
        int result = -1;
        try {
            result = weatherService.addUser(map);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}
