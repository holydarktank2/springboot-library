package com.yulun.library.controller;

import com.yulun.library.dto.UserLoginRequest;
import com.yulun.library.dto.UserRegisterRequest;
import com.yulun.library.model.User;
import com.yulun.library.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users/register")
    public ResponseEntity<User> register(@RequestBody @Valid UserRegisterRequest userRegisterRequest){
        Integer userId = userService.register(userRegisterRequest);

        User user = userService.getUserById(userId);

        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PostMapping("/users/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody @Valid UserLoginRequest userLoginRequest){
        String token = userService.login(userLoginRequest);

        Map<String, String> response  = new HashMap<>();
        response.put("token", token);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
