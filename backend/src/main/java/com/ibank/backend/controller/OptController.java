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
import com.ibank.backend.service.UserService;
import com.ibank.backend.vo.request.TopUpResquest;
import com.ibank.backend.vo.request.TranferRequest;
import com.ibank.backend.vo.response.TopUpResponse;
import com.ibank.backend.vo.response.TranferResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/api/opt")
public class OptController {
    
  @Autowired  
  private  UserService userService;
  
  @PostMapping(value="/topup")
  @ResponseBody
  public  TopUpResponse topup(@RequestBody TopUpResquest request,@RequestHeader HttpHeaders headers) throws Exception {
    log.info("topup request {}", JSON.toJSONString(request));
    log.info("topup headers {}", JSON.toJSONString(headers));
        return  new TopUpResponse();
     }   
     
  @PostMapping(value="/transfer")
  @ResponseBody
  public TranferResponse transfer(@RequestBody TranferRequest request,@RequestHeader HttpHeaders headers) {
    log.info("transfer request {}", JSON.toJSONString(request));
    log.info("transfer headers {}", JSON.toJSONString(headers));
    return  new TranferResponse();
  }  
    
}
