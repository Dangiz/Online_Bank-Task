package com.dangiz.onlinebank;

import com.dangiz.onlinebank.Data.UserRepository;
import com.dangiz.onlinebank.Entities.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import javax.persistence.*;
import java.util.Date;


@SpringBootApplication
public class OnlineBankApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineBankApplication.class, args);
	}

}
