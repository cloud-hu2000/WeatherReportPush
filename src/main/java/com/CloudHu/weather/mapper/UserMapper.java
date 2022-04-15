package com.CloudHu.weather.mapper;

import com.CloudHu.weather.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    public int addUser(User user);
}
