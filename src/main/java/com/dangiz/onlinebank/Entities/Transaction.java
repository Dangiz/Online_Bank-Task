package com.dangiz.onlinebank.Entities;

import lombok.Getter;
import org.aspectj.lang.annotation.Before;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class Transaction {
    public Transaction() {
    }
    @PostPersist
    public void MakeTran()
    {
        //System.out.println("Transaction post=persist is working");
        to.sum+=sum;
        from.sum-=sum;
    }
    public Transaction(BankCache from, BankCache to, float sum) {
        this.from = from;
        this.to = to;
        this.sum = sum;
        transactionDate=new Date();
        isRolledBack =false;
    }
    @Getter
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public Long id;
    @ManyToOne(cascade = CascadeType.REMOVE,optional = false)
    @Getter
    public BankCache from;
    @ManyToOne(cascade = CascadeType.REMOVE,optional = false)
    @Getter
    public BankCache to;
    @Getter
    public float sum;
    @Getter
    public Date transactionDate;
    @Getter
    public boolean isRolledBack;
    public void Rollback() {
        if(!isRolledBack) {
        isRolledBack =true;
        from.sum +=sum;
        to.sum -=sum;
        }
    }

}
