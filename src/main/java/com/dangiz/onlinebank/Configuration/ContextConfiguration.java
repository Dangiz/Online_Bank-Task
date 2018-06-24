/*package com.dangiz.onlinebank.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan("com.dangiz.onlinebank.Entities")
@EnableJpaRepositories
@EnableTransactionManagement
public class ContextConfiguration {
    @Bean
    public LocalEntityManagerFactoryBean entityManagerFactory() {
        LocalEntityManagerFactoryBean result =
                new LocalEntityManagerFactoryBean();
        result.setPersistenceUnitName("com.dangiz.onlinebank.Entities");
        return result;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager result = new JpaTransactionManager();
        result.setEntityManagerFactory(entityManagerFactory().getObject());
        return result;
    }
}*/