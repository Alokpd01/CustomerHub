package com.customerhub.mapper;

import com.customerhub.dto.UserDto;
import com.customerhub.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User mapToUser(UserDto userDto){
        User user = new User();

        user.setUserId(userDto.getUserId());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        user.setAmount(userDto.getAmount());

        return user;
    }

    public UserDto mapToUserDto(User userDto){
        UserDto user = new UserDto();

        user.setUserId(userDto.getUserId());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        user.setAmount(userDto.getAmount());

        return user;
    }

}
