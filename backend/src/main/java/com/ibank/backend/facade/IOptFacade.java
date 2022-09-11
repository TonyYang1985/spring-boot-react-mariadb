package com.ibank.backend.facade;

import com.ibank.backend.vo.request.TopUpResquest;
import com.ibank.backend.vo.request.TranferRequest;
import com.ibank.backend.vo.response.TopUpResponse;
import com.ibank.backend.vo.response.TranferResponse;

public interface IOptFacade {
    TopUpResponse topup(TopUpResquest request);
    TranferResponse transfer(TranferRequest request);
}
