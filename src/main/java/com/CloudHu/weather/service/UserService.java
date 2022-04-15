package com.CloudHu.weather.service;

import com.CloudHu.weather.entity.User;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface UserService {
    public int addUser(Map<String, String> map) throws IOException;

    public List<User> getAllUser();
}
