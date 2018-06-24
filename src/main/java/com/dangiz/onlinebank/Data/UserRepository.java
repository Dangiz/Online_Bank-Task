package com.dangiz.onlinebank.Data;

import java.util.List;

import com.dangiz.onlinebank.Entities.BankCache;
import com.dangiz.onlinebank.Entities.User;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByLastName(String lastName);
    User findByUserName(String username);
}


