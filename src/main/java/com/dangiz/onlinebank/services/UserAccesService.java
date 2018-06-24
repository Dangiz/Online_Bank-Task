package com.dangiz.onlinebank.services;

import com.dangiz.onlinebank.Data.CacheRepo;
import com.dangiz.onlinebank.Data.UserRepository;
import com.dangiz.onlinebank.Entities.BankCache;
import com.dangiz.onlinebank.Entities.InputTransactionState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component("AccessService")
public final class UserAccesService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    CacheRepo cacheRepo;

    public boolean transactionValidation(long cacheId, InputTransactionState transactionState)
    {
        return transactionState.Sum>0;
    }

    public boolean cacheAccessValidation(UserDetails principal, long cacheId) {
        BankCache bankCache=cacheRepo.findById(cacheId).get();
        return bankCache.user.userName.compareTo(principal.getUsername())==0;
    }

}