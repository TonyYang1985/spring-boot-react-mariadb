package com.ibank.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.ibank.backend.service.OptService;
import com.ibank.backend.utils.PraseJwtTest;
import com.ibank.backend.vo.request.TopUpResquest;
import com.ibank.backend.vo.request.TranferRequest;
import com.ibank.backend.vo.response.TopUpResponse;
import com.ibank.backend.vo.response.TranferResponse;

import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
@RequestMapping("/api/opt")
public class OptController {
    
  @Autowired  
  private  OptService optService;
  
  @PostMapping(value="/topup")
  @ResponseBody
  public  TopUpResponse topup(@RequestBody TopUpResquest request,@RequestHeader("Authorization") String token) throws Exception {
      //Claims cls =PraseJwtTest.tokenToOut(token.replace("Bearer ", ""));
      //log.info("topup save request {}",cls.get("jti"));
      return  optService.topup(request);
     }   
     
  @PostMapping(value="/transfer")
  @ResponseBody
  public TranferResponse transfer(@RequestBody TranferRequest request,@RequestHeader("Authorization") String token) {
    log.info("topup save request {}",token);
    return  optService.transfer(request);
  }  
    
}
