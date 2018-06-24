package com.dangiz.onlinebank.services;

import com.dangiz.onlinebank.Data.UserRepository;
import com.dangiz.onlinebank.Entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@Service
public class NewUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        if(username.compareTo("sa")==0)
            return org.springframework.security.core.userdetails.User.withDefaultPasswordEncoder()
            .username("sa")
            .password("password")
            .roles("ADMIN").build();
        User user=userRepository.findByUserName(username);
        return org.springframework.security.core.userdetails.User.withDefaultPasswordEncoder()
                .username(user.userName)
                .password(user.password)
                .roles("USER").build();
    }
}
