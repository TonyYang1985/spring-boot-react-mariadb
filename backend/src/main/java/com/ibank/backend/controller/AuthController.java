package com.ibank.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ibank.backend.service.UserService;
import com.ibank.backend.vo.request.LoginRequest;
import com.ibank.backend.vo.request.SignupRequest;
import com.ibank.backend.vo.response.JwtResponse;

@Controller
@RequestMapping("/api/auth")
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
    
}
