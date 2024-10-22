package com.customerhub.service;

import com.customerhub.dto.UserDto;

import java.util.List;
import java.util.Map;

public interface UserService {

    public String login(UserDto userDto);
    public UserDto register(UserDto userDto);
    public UserDto getUserById(String id);
    public List<UserDto> getAllUser();
    public UserDto updateUser(String id, Map<String , String > userMap);
    public String deleteUser(String id);
}
