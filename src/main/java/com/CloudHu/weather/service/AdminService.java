package com.CloudHu.weather.service;

import com.CloudHu.weather.dto.Result;
import com.CloudHu.weather.entity.UserWeather;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface AdminService {
    public Result<Integer> addUserWeather(Map<String, String> map) throws IOException;

    public List<UserWeather> getAllUserWeather();

    public void sendWeather() throws Exception;
}
