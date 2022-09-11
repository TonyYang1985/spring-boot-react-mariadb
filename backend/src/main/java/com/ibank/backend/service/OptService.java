
package com.ibank.backend.service;


import org.springframework.stereotype.Service;

import com.ibank.backend.facade.IOptFacade;

import com.ibank.backend.vo.request.TopUpResquest;
import com.ibank.backend.vo.request.TranferRequest;

import com.ibank.backend.vo.response.TopUpResponse;
import com.ibank.backend.vo.response.TranferResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OptService  implements IOptFacade {
    
    @Override
    public TopUpResponse topup(TopUpResquest request) {
        TopUpResponse resp = new TopUpResponse();
        return resp;
    }

    @Override
    public TranferResponse transfer(TranferRequest request) {
        TranferResponse resp = new TranferResponse();
        return resp;
    }}
    