package com.gfa.p2pchatapp.controllers;

import com.gfa.p2pchatapp.models.User;
import com.gfa.p2pchatapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    private final UserService userService;

    @Autowired
    public MainController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping({"/", ""})
    public String mainPage(){
        if (userService.getAllUsers().size() == 1) return "mainpage";
        else return "register";
    }

    @GetMapping("/register")
    public String getRegister(){
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String name, Model model){
        if (name != null && !name.isEmpty()) {
            userService.addUser(new User(name));
            return "redirect:/";
        } else {
            model.addAttribute("error", "The username field is empty.");
            return "register";
        }
    }
}
