package com.dangiz.onlinebank.Data;

import com.dangiz.onlinebank.Entities.BankCache;
import com.dangiz.onlinebank.Entities.Transaction;
import org.aspectj.lang.annotation.After;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.PostPersist;
import java.util.List;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction,Long> {
    List<BankCache> findAllByFrom(BankCache cache);
    List<BankCache> findAllByTo(BankCache cache);


}