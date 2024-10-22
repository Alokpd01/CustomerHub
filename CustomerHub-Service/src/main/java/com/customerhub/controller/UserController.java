package com.customerhub.controller;

import com.customerhub.dto.UserDto;
import com.customerhub.entity.JwtResponse;
import com.customerhub.service.impl.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("user")
@AllArgsConstructor
public class UserController {

    private UserServiceImpl userService;

    @PostMapping("login")
    public ResponseEntity<JwtResponse> login(@RequestBody UserDto userDto){
        JwtResponse jwtResponse = JwtResponse
                .builder()
                .jwtToken(userService.login(userDto))
                .username(userDto.getUsername()).build();
        return ResponseEntity.ok(jwtResponse);
    }

    @PostMapping("register")
    public UserDto register(@RequestBody UserDto userDto){
        return userService.register(userDto);
    }

    @GetMapping
    public List<UserDto> getAllUsers(){
        return userService.getAllUser();
    }

    @GetMapping("{id}")
    public UserDto getUserById(@PathVariable String  id){
        return userService.getUserById(id);
    }

    @PutMapping("{id}")
    public UserDto updateUser(@PathVariable String id, @RequestBody Map<String , String> userMap){
        return userService.updateUser(id, userMap);
    }

    @DeleteMapping("{id}")
    public String deleteUser(@PathVariable String id){
        return userService.deleteUser(id);
    }

}
