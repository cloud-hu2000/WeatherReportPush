package com.CloudHu.weather.mapper;

import com.CloudHu.weather.entity.UserWeather;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminMapper {

    public int addUserWeather(UserWeather user);

    public List<UserWeather> getAllUserWeather();

}
