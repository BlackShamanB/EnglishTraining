package com.example.zubarev.englishtraining.englishtraining.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

import com.example.zubarev.englishtraining.englishtraining.model.Role;
import com.example.zubarev.englishtraining.englishtraining.model.User;
import com.example.zubarev.englishtraining.englishtraining.repos.UserRepo;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("users", userRepo.findAll());
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model) {
        User userFromDb = userRepo.findByUsername(user.getUsername());

        if (userFromDb != null) {
            model.put("message", "User exists!");
            return "registration";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);

        return "redirect:/login";
    }
}
