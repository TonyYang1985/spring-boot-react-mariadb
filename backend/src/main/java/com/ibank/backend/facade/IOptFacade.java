package com.ibank.backend.facade;

import com.ibank.backend.vo.request.TopUpResquest;
import com.ibank.backend.vo.request.TranferRequest;
import com.ibank.backend.vo.response.TopUpResponse;
import com.ibank.backend.vo.response.TranferResponse;

import io.jsonwebtoken.Claims;

public interface IOptFacade {
    TopUpResponse topup(TopUpResquest request,  Claims cls);
    TranferResponse transfer(TranferRequest request, Claims cls);
}
