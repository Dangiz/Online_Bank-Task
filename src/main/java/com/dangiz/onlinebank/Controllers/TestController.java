package com.dangiz.onlinebank.Controllers;

import com.dangiz.onlinebank.Data.UserRepository;
import com.dangiz.onlinebank.Entities.User;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller
public class TestController {
    final UserRepository repo;

    public TestController(UserRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/ControlTesting")
    @PreAuthorize("hasRole('ADMIN')")
    public ModelAndView index() {
        Iterable<User> data=repo.findAll();
        Map<String, Iterable<User>> model = new HashMap<>();
        model.put("name", data);
        return new ModelAndView("greeting", model);

    }
}