package com.dangiz.onlinebank.Entities;


import com.dangiz.onlinebank.Data.TransactionRepo;
import lombok.Getter;
import lombok.Setter;

import javax.naming.Name;
import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class BankCache {
    public BankCache() {
    }

    public BankCache(User user,String name) {
        this.user = user;
        this.name=name;
    }
    @Getter
    @OneToMany(mappedBy = "from",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    public List<Transaction> outTransactions;
    @Getter
    @OneToMany(mappedBy = "to",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    public List<Transaction> inTransactions;
    @Getter
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public  Long id;
    @Setter
    @Getter
    public float sum;
    @Getter
    @ManyToOne(cascade = CascadeType.REMOVE,fetch=FetchType.EAGER)
    public User user;
    @Getter
    @Setter
    public String name;
    @Override
    public String toString() {
        return "BankCache{" +
                "id=" + id +
                ", sum=" + sum +
                '}';
    }
}
