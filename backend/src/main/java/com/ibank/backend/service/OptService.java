
package com.ibank.backend.service;


import org.apache.commons.collections.CollectionUtils;
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

import java.util.Date;
import java.util.List;

import com.ibank.backend.entity.UserDebt;
import com.ibank.backend.entity.UserBalance;
import com.ibank.backend.entity.Transaction;
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
        int userId= (int) cls.get("jti");
        List<UserDebt>  userDebts= this.debtRepo.findUserDebtByUserId("Y",userId);
        // no debt
        if(CollectionUtils.isEmpty(userDebts)){
            // add balance
            UserBalance balance = new UserBalance();
            balance.setBalance(request.amount);
            balance.setStatus("Y");
            balance.setUserId(userId);
            balance.setCreatedBy((String) cls.get("jti"));
            balance.setUpdatedAt(new Date());
            balance.setUpdatedBy((String) cls.get("jti"));
            balanceRepo.save(balance);
        }else{
            double totalDebt = this.debtRepo.sumUserDebtByUserId("Y",userId);
            double realBalance = 0;
            if(request.amount>=totalDebt){
                double difference=request.amount-totalDebt;
                realBalance =difference>=0?difference:0;
            }else{
                

            }
            // add balance
             UserBalance balance = new UserBalance();
             balance.setBalance(realBalance);
             balance.setStatus("Y");
             balance.setUserId(userId);
             balance.setCreatedBy((String) cls.get("jti"));
             balance.setUpdatedAt(new Date());
             balance.setUpdatedBy((String) cls.get("jti"));
             balanceRepo.save(balance);

        }
        // add Transaction
        Transaction trans= new Transaction();
            trans.setStatus("Y");
            trans.setUserId(userId);
            trans.setAmount(request.amount);
            trans.setType("TopUp");
            trans.setCreatedBy((String) cls.get("jti"));
            trans.setUpdatedAt(new Date());
            trans.setUpdatedBy((String) cls.get("jti"));
        transRepo.save(trans);

        return resp;
    }

    @Override
    public TranferResponse transfer(TranferRequest request, Claims cls) {
        TranferResponse resp = new TranferResponse();
        return resp;
    }}
    