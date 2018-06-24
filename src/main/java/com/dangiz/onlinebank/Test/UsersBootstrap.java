package com.dangiz.onlinebank.Test;

import com.dangiz.onlinebank.Data.CacheRepo;
import com.dangiz.onlinebank.Data.TransactionRepo;
import com.dangiz.onlinebank.Data.UserRepository;
import com.dangiz.onlinebank.Entities.BankCache;
import com.dangiz.onlinebank.Entities.Transaction;
import com.dangiz.onlinebank.Entities.User;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class UsersBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private UserRepository userRepo;
    private CacheRepo cacheRepo;

    private TransactionRepo transactionRepo;

    public UsersBootstrap(UserRepository repo, CacheRepo cacheRepo, TransactionRepo transactionRepo) {
        this.userRepo = repo;
        this.cacheRepo = cacheRepo;
        this.transactionRepo = transactionRepo;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData() {
        User natasha = new User("Natasha", "Natasha", "Volkina", new Date(), "qwerty");
        userRepo.save(new User("Doma", "Dmitry", "Vladimov", new Date(), "qwerty"));
        userRepo.save(new User("Tolyan", "Anatoly", "Ivanov", new Date(), "qwerty"));
        userRepo.save(natasha);
        User Natalie=userRepo.findById(new Long(3)).get();
        BankCache bankCache = new BankCache(Natalie, "Cache 3");
        bankCache.sum = 900;
        BankCache bankCache2=new BankCache(Natalie, "Cache 1");
        Natalie.Caches.add(bankCache);
        Natalie.Caches.add(bankCache2);
        userRepo.save(Natalie);
        userRepo.save(new User("Vanya", "Ivan", "Petrov", new Date(1995, 11, 1), "qwerty"));
        //Making of Transactions
        BankCache from= cacheRepo.findById((long)4).get();
        BankCache to=cacheRepo.findById((long)5).get();
        Transaction tran=new Transaction(from,to,400);
        Transaction tran1=new Transaction(from,to,100);
        tran.MakeTran();
        tran1.MakeTran();
        from.outTransactions.add(tran);
        from.outTransactions.add(tran1);
        cacheRepo.save(from);
        cacheRepo.save(to);
        //Transaction rollback
        BankCache tempCache= cacheRepo.findById((long)4).get();
        Transaction tempTran=tempCache.outTransactions.get(1);
        BankCache tempCache2=tempTran.to;
        tempTran.Rollback();
        cacheRepo.save(tempCache);
        cacheRepo.save(tempCache2);
    }
}
