package com.customerhub.service.impl;

import com.customerhub.dto.UserDto;
import com.customerhub.entity.User;
import com.customerhub.exception.EmailAlreadyExistException;
import com.customerhub.exception.ResourceNotFoundException;
import com.customerhub.exception.UsernameAlreadyExistException;
import com.customerhub.mapper.UserMapper;
import com.customerhub.repository.UserRepository;
import com.customerhub.service.UserService;
import com.customerhub.util.JwtUtil;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    public static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    private PasswordEncoder passwordEncoder;
    private UserDetailsService userDetailsService;
    private AuthenticationManager manager;
    private JwtUtil helper;
    private UserMapper userMapper;

    @Override
    public String login(UserDto userDto) {
        doAuthenticate(userDto.getUsername(), userDto.getPassword());
        UserDetails userDetails = userDetailsService.loadUserByUsername(userDto.getUsername());
        return this.helper.generateToken(userDetails);
    }

    @Override
    public UserDto register(UserDto userDto) {
        userDto.setUserId(UUID.randomUUID().toString());
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));

        Optional<User> optionalUserByUsername = userRepository.findByUsername(userDto.getUsername());
        Optional<User> optionalUserByEmail = userRepository.findByEmail(userDto.getEmail());

        if(optionalUserByUsername.isPresent()){
            throw new UsernameAlreadyExistException("Username already exist");
        }

        if(optionalUserByEmail.isPresent()){
            throw new UsernameAlreadyExistException("Email already exist");
        }

        userRepository.save(userMapper.mapToUser(userDto));
        LOGGER.info("User registered successfully.");
        return userDto;
    }

    @Override
    public UserDto getUserById(String id) {
        return userMapper.mapToUserDto(userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "ID", id)));
    }

    @Override
    public List<UserDto> getAllUser() {
        return userRepository.findAll().stream().map((user) -> userMapper.mapToUserDto(user)).toList();
    }

    @Override
    public UserDto updateUser(String id, Map<String, String> userMap) {

        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "ID", id));
        user.setUserId(id);

        if(userMap.containsKey("firstName")){
            user.setFirstName(userMap.get("firstName"));
        }

        if(userMap.containsKey("lastName")){
            user.setLastName(userMap.get("lastName"));
        }

        if(userMap.containsKey("email")){
            Optional<User> optionalUserByEmail = userRepository.findByEmail(userMap.get("email"));
            if(optionalUserByEmail.isPresent()){
                throw new EmailAlreadyExistException("Email already exist");
            }
            user.setEmail(userMap.get("email"));
        }

        if(userMap.containsKey("amount")){
            user.setAmount(Long.valueOf(userMap.get("amount")));
        }

        if(userMap.containsKey("password")){
            user.setPassword(userMap.get("password"));
        }

        LOGGER.info("User updated successfully.");
        return userMapper.mapToUserDto(userRepository.save(user));
    }

    @Override
    public String deleteUser(String id) {
        getUserById(id);
        userRepository.deleteById(id);
        LOGGER.info("User deleted successfully.");
        return "User deleted successfully";
    }

    private void doAuthenticate(String username, String password) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, password);
        try {
            manager.authenticate(authentication);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }
    }
}
