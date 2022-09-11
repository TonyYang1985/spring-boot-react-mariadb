
package com.ibank.backend.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibank.backend.facade.IOptFacade;
import com.ibank.backend.repository.TransactionRepository;
import com.ibank.backend.repository.UserBalanceRepository;
import com.ibank.backend.repository.UserDebtRepository;
import com.ibank.backend.vo.request.TopUpResquest;
import com.ibank.backend.vo.request.TranferRequest;

import com.ibank.backend.vo.response.TopUpResponse;
import com.ibank.backend.vo.response.TranferResponse;

import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OptService  implements IOptFacade {
    
    @Autowired  
    private  UserDebtRepository debtRepo;
    
    @Autowired  
    private  UserBalanceRepository balanceRepo;

    @Autowired  
    private  TransactionRepository transRepo;


    @Override
    public TopUpResponse topup(TopUpResquest request, Claims cls) {
        TopUpResponse resp = new TopUpResponse();
        return resp;
    }

    @Override
    public TranferResponse transfer(TranferRequest request, Claims cls) {
        TranferResponse resp = new TranferResponse();
        return resp;
    }}
    