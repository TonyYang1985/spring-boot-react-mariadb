package com.ibank.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ibank.backend.interfaces.annotation.AutoRegisterController;
import com.ibank.backend.service.UserService;
import com.ibank.backend.vo.request.CreateUserRequest;
import com.ibank.backend.vo.request.DeleteUserRequest;
import com.ibank.backend.vo.request.UpdateUserRequest;
import com.ibank.backend.vo.response.CreateUserResponse;
import com.ibank.backend.vo.response.DeleteUserResponse;
import com.ibank.backend.vo.response.ListUserResponse;
import com.ibank.backend.vo.response.UniqueUserResponse;
import com.ibank.backend.vo.response.UpdateUserResponse;

import lombok.extern.slf4j.Slf4j;

import com.alibaba.fastjson.JSON;


@Slf4j
@Controller
@RequestMapping("/api/user")
public class UserController {
    
    @Autowired  
    private  UserService userService;
    
    @GetMapping(value="/{id}/enity")
    @ResponseBody
    public UniqueUserResponse getUserById(@PathVariable int id ) {
        return userService.findById(id);
    } 

    @GetMapping
    @ResponseBody
    public ListUserResponse getAllUsers(){
        return userService.getAllUsers();
    }   

    @PostMapping
    @ResponseBody
    public CreateUserResponse createUser(@RequestBody CreateUserRequest request) {
        log.info("Simple log statement with request {}", JSON.toJSONString(request));
        return userService.createUser(request);
     }           
     
    @PutMapping
    @ResponseBody
    public UpdateUserResponse updateUser(@RequestBody UpdateUserRequest request) {
        return userService.updateUser(request);   
    }           
    @DeleteMapping
    @ResponseBody
    public DeleteUserResponse deleteStudent(@RequestBody DeleteUserRequest request) {
        return userService.deleteById(request);      
    }
}
