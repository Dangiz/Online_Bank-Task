package com.dangiz.onlinebank.Entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table
public class User {
    @Getter
    @Setter
    @Column(unique=true)
    public String userName;
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Getter
    public Long id;
    @Getter
    @Setter
    public String firstName;
    @Getter
    @Setter
    public String lastName;
    @Getter
    @Setter
    public String password;
    @Getter
    @Setter
    public Date birthDate;
    @Getter
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    public List<BankCache> Caches;

    public User(String userName,String firstName, String lastName, Date birthDate,String password) {
        this.userName=userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        Caches=new LinkedList<>();
        this.password=password;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{"+"UserName "+userName +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate + '}';
    }

}
