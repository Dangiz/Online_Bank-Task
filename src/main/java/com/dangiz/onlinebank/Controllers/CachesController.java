package com.dangiz.onlinebank.Controllers;

import com.dangiz.onlinebank.Data.CacheRepo;
import com.dangiz.onlinebank.Entities.BankCache;
import com.dangiz.onlinebank.Entities.InputTransactionState;
import com.dangiz.onlinebank.Entities.Transaction;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class CachesController {
    CacheRepo cacheRepo;

    public CachesController(CacheRepo cacheRepo) {
        this.cacheRepo = cacheRepo;
    }

    public void updateCache(BankCache cache)
    {
        cacheRepo.save(cache);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value="/users/details/{userId}/cache_info",method = RequestMethod.GET)
    public ModelAndView showCacheInfo(@Param("userId") Long userId, @Param("cacheId") Long cacheId) {
        Map<String, Object> model = new HashMap<>();
        BankCache bankCache=cacheRepo.findById(cacheId).get();
        model.put("bankCache",bankCache);
        model.put("outTrans",bankCache.outTransactions);
        model.put("inTrans",bankCache.inTransactions);
        return new ModelAndView("cacheInfo",model);
    }

    @PreAuthorize("(hasRole('USER'))and(@AccessService.cacheAccessValidation(principal,#cacheId))")
    @RequestMapping(value="/profile/cache_info",method = RequestMethod.GET)
    public ModelAndView showCacheInfo(@Param("cacheId") Long cacheId) {
            BankCache bankCache=cacheRepo.findById(cacheId).get();
            Map<String, Object> model = new HashMap<>();
            model.put("bankCache",bankCache);
            model.put("outTrans",bankCache.outTransactions);
            model.put("inTrans",bankCache.inTransactions);
            return new ModelAndView("cacheInfo", model);
    }


    @PreAuthorize("(hasRole('USER'))and(@AccessService.cacheAccessValidation(principal,#cacheId))")
    @RequestMapping(value="/profile/new_transaction",method = RequestMethod.GET)
    public String CreateTransaction(@Param("cacheId") Long cacheId, Model model) {
        model.addAttribute("tran",new InputTransactionState());
        model.addAttribute("senderCahceId",cacheId);
        return "TransactionCreate";
    }

    @PreAuthorize("(hasRole('USER'))and(@AccessService.cacheAccessValidation(principal,#cacheId))")
    @RequestMapping(value="/profile/new_transaction",method = RequestMethod.POST)
    public String CreateTransaction(@ModelAttribute InputTransactionState tran,@Param("cacheId") Long cacheId, BindingResult bindingResult, Model model) {
        BankCache sender=cacheRepo.findById(cacheId).get();
        BankCache recipient=cacheRepo.findById(tran.RecipientId).get();
        Transaction transaction=new Transaction(sender,recipient,tran.Sum);
        recipient.inTransactions.add(transaction);
        cacheRepo.save(sender);
        cacheRepo.save(recipient);
        return "redirect: ../../";
    }
}
