package com.ibank.backend.controller;

import com.ibank.backend.vo.response.UniqueUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.ibank.backend.interfaces.annotation.AutoRegisterController;
import com.ibank.backend.service.UserService;
import com.ibank.backend.vo.request.LoginRequest;
import com.ibank.backend.vo.request.SignupRequest;
import com.ibank.backend.vo.response.JwtResponse;



@AutoRegisterController
@Controller
@RequestMapping(value="/api/auth",name="2223")
public class AuthController {
    
  @Autowired  
  private  UserService userService;
  
  @PostMapping(value="/signin")
  @ResponseBody
  public JwtResponse authenticate(@RequestBody LoginRequest request) throws Exception {
        return  userService.authenticate(request);
     }   
     
  @PostMapping(value="/signup")
  @ResponseBody
  public JwtResponse register(@RequestBody SignupRequest request) {
    return  userService.signUp(request);
  }

  @GetMapping(value="/{id}/enity")
  @ResponseBody
  public UniqueUserResponse getUserById(@PathVariable int id ) {
    return null;
  }

  @GetMapping(value="/{id}/user")
  @ResponseBody
  public UniqueUserResponse getUserId2(int id ) {
    return null;
  }

}
