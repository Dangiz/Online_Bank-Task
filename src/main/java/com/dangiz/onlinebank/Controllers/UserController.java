package com.dangiz.onlinebank.Controllers;

import com.dangiz.onlinebank.Data.CacheRepo;
import com.dangiz.onlinebank.Data.TransactionRepo;
import com.dangiz.onlinebank.Data.UserRepository;
import com.dangiz.onlinebank.Entities.BankCache;
import com.dangiz.onlinebank.Entities.Transaction;
import com.dangiz.onlinebank.Entities.User;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;



@Controller
public class UserController {
    final UserRepository repo;

    public UserController(UserRepository repo) {
        this.repo = repo;
    }

    @RequestMapping(value="/new_user",method = RequestMethod.GET)
    public String newUser(Model model) {
        model.addAttribute("user",new User());
        return "registration";
    }

    @RequestMapping(value="/new_user",method = RequestMethod.POST)
    public String newUser(@ModelAttribute User user, BindingResult bindingResult, Model model) {
        repo.save(user);
        return "";
    }

    @RequestMapping(value="/users",method = RequestMethod.GET)
    public String index(Model model) {
        Iterable<User> data=repo.findAll();
        model.addAttribute("users",data);
        return "users";

    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value="/users/details",method = RequestMethod.GET)
    public ModelAndView showUser(@Param("userId") Long userId) {
        Map<String, Object> model = new HashMap<>();
        User user=repo.findById(userId).get();
        model.put("user",user);
        model.put("caches",user.Caches);
        return new ModelAndView("userInfo",model);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value="/users/doDelete",method = RequestMethod.POST )
    public String delete(@Param("userId") Long userId, Model model) {
        repo.deleteById(userId);
        return "redirect:/users";
    }

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value="/profile",method = RequestMethod.GET)
    public ModelAndView profile()
    {
        UserDetails det= (UserDetails) org.springframework.security.core.context.SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        Map<String, Object> model = new HashMap<>();
        User user=repo.findByUserName(det.getUsername());
        model.put("user",user);
        model.put("caches",user.Caches);
        return new ModelAndView("userInfo",model);
    }

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value="/profile/new_cache",method = RequestMethod.GET)
    public String CreateTransaction(Model model)
    {
        model.addAttribute("newCache",new BankCache());
        return "cacheCreate";
    }

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value="/profile/new_cache",method = RequestMethod.POST)
    public String CreateTransaction(@ModelAttribute BankCache newCache, Model model)
    {
        UserDetails det= (UserDetails) org.springframework.security.core.context.SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        User user=repo.findByUserName(det.getUsername());
        newCache.user=user;
        user.Caches.add(newCache);
        repo.save(user);
        return "redirect:/profile";
    }

}