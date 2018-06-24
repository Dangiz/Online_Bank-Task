package com.dangiz.onlinebank.Controllers;

import com.dangiz.onlinebank.Data.TransactionRepo;
import com.dangiz.onlinebank.Entities.Transaction;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TransactionsController {

    TransactionRepo transactionRepo;

    public TransactionsController(TransactionRepo transactionRepo) {
        this.transactionRepo = transactionRepo;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value="/transactions",method = RequestMethod.GET)
    public String index(Model model) {
        Iterable<Transaction> data=transactionRepo.findAll();
        model.addAttribute("transactions",data);
        return "transactionsList";

    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value="/transactions/doRollback",method = RequestMethod.POST )
    public String delete(@Param("transactionId") Long transactionId, Model model) {
        Transaction transaction=transactionRepo.findById(transactionId).get();
        transaction.Rollback();
        transactionRepo.save(transaction);
        System.out.println("Transaction rollback controller called");
        return "redirect:/transactions";
    }
}
