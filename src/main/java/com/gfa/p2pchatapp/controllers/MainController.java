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
    public String mainPage(Model model) throws Exception {
        if (userService.getAllUsers().size() == 1) {
            model.addAttribute("name", userService.getName(1L));
            return "mainpage";
        }
        else return "register";
    }

    @PostMapping({"", "/"})
    public String changeName(@RequestParam String name, Model model) throws Exception {
        if (name != null && !name.isEmpty()) {
            userService.changeName(1L, name);
            model.addAttribute("name", name);
            model.addAttribute("nameChanged", "Name changed!");
            return "mainpage";
        } else {
            model.addAttribute("error", "The username field is empty.");
            return "mainpage";
        }

    }

    @GetMapping("/register")
    public String getRegister(Model model) throws Exception {
        if (userService.getAllUsers().size() == 1) {
            model.addAttribute("name", userService.getName(1L));
            return "mainpage";
        }
        else return "register";
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
