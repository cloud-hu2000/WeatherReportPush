package com.CloudHu.weather.mapper;

import com.CloudHu.weather.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    public int addUser(User user);

    public List<User> getAllUser();
}
