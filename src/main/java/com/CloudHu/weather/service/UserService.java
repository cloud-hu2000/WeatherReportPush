package com.CloudHu.weather.service;

import java.io.IOException;
import java.util.Map;

public interface UserService {
    public int addUser(Map<String, String> map) throws IOException;
}
